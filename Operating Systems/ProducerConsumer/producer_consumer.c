#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>

struct Node {
    int value;
    struct Node* next;
    struct Node* prev;
};

struct Node* head = NULL;
int numberOfNodes = 0;

// Creates a new node and returns it
struct Node* createNewNode(int val) {
  struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
  newNode->value = val;
  newNode->next = NULL;
  newNode->prev = NULL;

  return newNode;
}

//Inserts a node at the head of the list
void insertNodeAtHead(int val) {
    struct Node* newNode = createNewNode(val);
    if (head == NULL) {
        head = newNode;
        return;
    }

    head->prev = newNode;
    newNode->next = head;
    head = newNode;
}

// Insert a node at the end of the linked list
void insertNodeAtTail(int val) {
    struct Node* temp = head;
    struct Node* newNode = createNewNode(val);

    if (head == NULL) {
        head = newNode;
        return;
    }

    while(temp->next != NULL) {
        temp = temp->next;
    }

    temp->next = newNode;
    newNode->prev = temp;
    numberOfNodes++;
}

void generateInitialValues() {
    int i = 0;
    // Generate three nodes and insert them in the list
    for (i = 0; i < 3; i++) {
        insertNodeAtTail(rand() % 40);
    }
}

void printList() {
    struct Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->value);
        temp = temp->next;
    }
    printf("\n");
}

void *Processor(void* genEven) {
    int randNum;
    int generateEvenNumbers = (long) genEven;
    while (1) {
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
        if (numberOfNodes < 20) {
            printf("Inserting %d into linked list, ", randNum);
            insertNodeAtTail(randNum);
            printf("list has %d nodes.\n", numberOfNodes);
            printList();
        }
        else {
            printf("Linked list is full. Waiting\n");
        }
    }
    pthread_exit(NULL);
}

int main() {
    // Setup random number generator
    srand(time(NULL));
    generateInitialValues();
    printList();

    pthread_t threads[2];
    int rc;
    long t;
    for(t = 0; t < 2; t++){
        printf("In main: creating thread %ld\n", t);
        rc = pthread_create(&threads[t], NULL, Processor, (void *)t);
        if (rc){
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }

    /* Last thing that main() should do */
    pthread_exit(NULL);
    return 0;
}
