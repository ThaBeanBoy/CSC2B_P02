# Custom Protocol

## Table of Content

## Introduction

For this practical, our practical lecturer made a custom protocol for identifying languages 
for the University of Johannesburg. **It's nothing advanced, it's just identifying words in the request**

## Description of custom protocol

The [practical pdf](./docs/ACSSE_CSC02B2_2023_Practical02.pdf) goes in depth on this protocol.

Participants:
- [C] represents the client
- [S] represents the server

Protocol:

- [C] : Clients connects to the server
- [S] : Sends the message : **"HELLO - you may make 4 requests and I’ll try to detect your language"**
- [C] : Sends the message : **START** to start the language analysis.
- [S] : Sends the message : **** indicating that the client can start sending:
  - **REQUEST <Message>** : REQUEST indicates that the client want's the server to analyse the **Message** for language detection.  
  - **DONE** : Indicates that the client is done sending requests.

## Server Socket

### Never ending loop

### Working with clients sockets

### Multi-threading

## Client class

### Extending Thread Class

### Allowing multiple clients