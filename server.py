from socket import *
import sys

serverPort = 6789
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('172.19.123.142', serverPort))
serverSocket.listen(1)

print('Ready to serve...')

while True:
    connectionSocket, addr = serverSocket.accept()
    try:
        message = connectionSocket.recv(1024).decode()  # Receive message from client
        filename = message.split()[1]  # Extract filename from request

        # Try to open and read the requested file
        try:
            f = open(filename[1:], 'r')
            outputdata = f.read()
            f.close()

            # Send HTTP response headers
            response_headers = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: {}\r\n\r\n".format(len(outputdata))
            connectionSocket.send(response_headers.encode())

            # Send the content of the requested file to the client
            connectionSocket.sendall(outputdata.encode())
        except IOError:
            if filename == "/HelloWorld.html":
                # If the requested filename is "HelloWorld.html" and it doesn't exist, return 404 Not Found
                error_response = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n<html><head></head><body><h1>404 Not Found</h1><p>The requested file was not found on this server.</p></body></html>"
                connectionSocket.send(error_response.encode())
            else:
                # If any other file doesn't exist, return 404 Not Found
                error_response = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n<html><head></head><body><h1>404 Not Found</h1><p>The requested file was not found on this server.</p></body></html>"
                connectionSocket.send(error_response.encode())

        connectionSocket.close()
    except:
        # Close the connection if an exception occurs
        connectionSocket.close()

serverSocket.close()
sys.exit()
