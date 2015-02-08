/*
Brute force tool
Written by: Gunnar Holwerda

For: CSCI-476
*/

#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h> //Include the getopt library see: http://www.gnu.org/software/libc/manual/html_node/Using-Getopt.html#Using-Getopt
#include <string.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define MAXRCVLEN 500
#define PORTNUM 22

void printHelp();

int main(int argc, char **argv)
{
	int singlePasswordFlag = 0, singleUserFlag = 0;
	int bflag = 0;
	char *passwordFileName = NULL, *userFileName = NULL, *ipAddress = NULL;
 	int index;
 	int c;
	
	opterr = 0;
	while ((c = getopt (argc, argv, "abc:")) != -1)
	{
		switch (c)
	    	{
	    		case 'h':
	    			printHelp();
	    			break;
	    		case 'p':
	  			singlePasswordFlag = 1;
	  			break;
	    		case 'P':
				passwordFileName = optarg;
				break;
	    		case 'l':
				singleUserFlag = 1;
				break;
			case "L":
				userFileName = optarg;
				break;
			case "i":
				ipAddress = optarg;
				break;
	    		case '?':
	      			if (optopt == 'c')
	        				fprintf (stderr, "Option -%c requires an argument.\n", optopt);
	      			else if (isprint (optopt))
	        				fprintf (stderr, "Unknown option `-%c'.\n", optopt);
	      			else
	        				fprintf (stderr, "Unknown option character `\\x%x'.\n", optopt);
				return 1;
				default: abort ();
	    	}
	}

	if (*ipAddress == NULL)
	{
		fprintf(stderr, "The -i option is a required option\n", );
		return 0;
	}

	char buffer[MAXRCVLEN + 1]; /* +1 so we can add null terminator */

	int len, mysocket;
	struct sockaddr_in dest;  

	mysocket = socket(AF_INET, SOCK_STREAM, 0); 
	memset(&dest, 0, sizeof(dest));                /* zero the struct */

	dest.sin_family = AF_INET;
	dest.sin_addr.s_addr = htonl(*ipAddress); 	/* set destination IP number - localhost, 127.0.0.1*/ 
	dest.sin_port = htons(PORTNUM);                	/* set destination port number */ 

	connect(mysocket, (struct sockaddr *)&dest, sizeof(struct sockaddr)); 
	len = recv(mysocket, buffer, MAXRCVLEN, 0); 

	/* We have to null terminate the received data ourselves */
	buffer[len] = '\0'; 
	printf("Received %s (%d bytes).\n", buffer, len); 
	close(mysocket);
	
	return EXIT_SUCCESS;
    	/*
	hacked = 0;
	Figure out how to open port 22 on a server see: http://en.wikibooks.org/wiki/C_Programming/Networking_in_UNIX
	while(!hacked)
	{
		Then figure out how to send the user option as the login
		And figure out how to send the password

		Get the response
		if (response == success) 
		{
			Then we have done it, we print the user and pass that was found to work and how many tries it took
			hacked = 1;
		}
		else if (response ==  error)
		{
			Print the error message
		}
		else
		{
			We failed so go back to top of loop
		}
	}
	*/

    	return 0;
}

void printHelp()
{
	printf("Help:\n\n");
	printf("-h\tPrint the help dialog as seen here\n");
	printf("-p -P\tPassword (-P for path to password file)\n");
	printf("-l -L\tUser (-L for path to user file\n");
	printf("-i\tIP Address\n");
	printf("Ex:\ntool -l test -p changeme -i 192.169.1.1\n");
}