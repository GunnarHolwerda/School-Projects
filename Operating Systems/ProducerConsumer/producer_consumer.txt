#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>
#include <unistd.h>

struct Node {
    int value;
    struct Node* next;
    struct Node* prev;
};

struct Node* head = NULL;
int numberOfNodes = 0;
pthread_mutex_t mutex;

// Creates a new node and returns it
struct Node* createNewNode(int val) {
  struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
  newNode->value = val;
  newNode->next = NULL;
  newNode->prev = NULL;

  return newNode;
}

// Prints out the linked list
void printList() {
    struct Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->value);
        temp = temp->next;
    }
    printf("\n");
}

// Insert a node at the end of the linked list
void insertNodeAtTail(int val) {
    pthread_mutex_lock(&mutex);
    if (numberOfNodes > 19) {
        printf("Linked list is full. Waiting...\n");
        pthread_mutex_unlock(&mutex);
        return;
    }

    printf("Inserting %d into linked list:\n",
        val
    );

    // Print the list before access
    printList();
    struct Node* temp = head;
    struct Node* newNode = createNewNode(val);

    if (head == NULL) {
        head = newNode;
        numberOfNodes++;
        pthread_mutex_unlock(&mutex);
        return;
    }

    while(temp->next != NULL) {
        temp = temp->next;
    }

    temp->next = newNode;
    newNode->prev = temp;
    numberOfNodes++;
    // Print the list after access
    printList();
    pthread_mutex_unlock(&mutex);
}

// Removes a node starting from the head of the list
void removeNodeFromHead(int removeEven) {
    pthread_mutex_lock(&mutex);
    // If we have 0 nodes then we can't remove and return
    if (numberOfNodes == 0 || head == NULL) {
        printf("List is empty. Waiting.");
        pthread_mutex_unlock(&mutex);
        return;
    }

    printf("Removing %s number from linked list:\n",
        removeEven == 1 ? "even" : "odd"
    );

    // Print the list before we access it
    printList();
    struct Node* temp = head;

    while (temp != NULL) {
        if (
            (temp->value % 2 == 0 && removeEven == 1) ||
            (temp->value % 2 != 0 && removeEven == 0)
        ) {
            // Remove node by setting previous nodes next to
            // current nodes next
            if (temp->prev != NULL) {
                // We are not replacing the head
                temp->prev->next = temp->next;
                // Check if we are replacing the last node in the list
                if (temp->next != NULL) {
                    temp->next->prev = temp->prev;
                }
            }
            else {
                // We are removing the head
                if (temp->next != NULL) {
                    temp->next->prev = NULL;
                    head = temp->next;
                }
                else {
                    // Removing the only node left
                    head = NULL;
                }
            }
            numberOfNodes--;
            // Print the list after we access it
            printList();
            pthread_mutex_unlock(&mutex);
            return;
        }
        temp = temp->next;
    }
    pthread_mutex_unlock(&mutex);
}

// Inserts three nodes into the list to begin with
void generateInitialValues() {
    int i = 0;
    // Generate three nodes and insert them in the list
    for (i = 0; i < 3; i++) {
        insertNodeAtTail(rand() % 40);
    }
}

// Producer function that adds a random even or odd number depending on the
// genEven paramter to the linked list
void *Producer(void* genEven) {
    int randNum;
    int generateEvenNumbers = (long) genEven;
    int numCycles = 20;
    int curCycle;
    for (curCycle = 0; curCycle < numCycles; curCycle++) {
        // Generate a random odd number or even number
        if (generateEvenNumbers == 1) {
            randNum = 1;
            while (randNum % 2 != 0) {
                randNum = rand() % 40;
            }
        }
        else {
            randNum = 0;
            while (randNum % 2 == 0) {
                randNum = rand() % 40;
            }
        }

        insertNodeAtTail(randNum);
        sleep(1);
    }
    pthread_exit(NULL);
}

// Consumer function that removes the first even or odd number depending on the
// consumeEven paramter to the linked list
void *Consumer(void* consumeEven) {
    int consumeEvenNumbers = (long) consumeEven;
    int numCycles = 20;
    int curCycle;
    for (curCycle = 0; curCycle < numCycles; curCycle++) {
        removeNodeFromHead(consumeEvenNumbers);
        sleep(1);
    }
    pthread_exit(NULL);
}

int main() {
    // Setup random number generator
    srand(time(NULL));
    generateInitialValues();
    pthread_mutex_init(&mutex, NULL);
    printf("Starting Producers and Consumers\n");
    pthread_t threads[4];
    int rc;
    long t;

    // Create producers
    for (t = 0; t < 2; t++) {
        rc = pthread_create(&threads[t], NULL, Producer, (void *)t);
        if (rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }

    // Create consumers
    for (t = 0; t < 2; t++) {
        rc = pthread_create(&threads[t + 2], NULL, Consumer, (void *) t);
        if (rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }

    /* Last thing that main() should do */
    pthread_exit(NULL);
    return 0;
}
