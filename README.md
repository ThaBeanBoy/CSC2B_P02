# Custom Protocol

## Table of Content

- [Introduction](#introduction)
- [Description of custom protocol](#description-of-custom-protocol)
- [Server Socket](#server-socket)
  * [Never ending loop](#never-ending-loop)
  * [Working with clients sockets](#working-with-clients-sockets)
  * [Multi-threading](#multi-threading)
- [Client class](#client-class)
  * [Extending Thread Class](#extending-thread-class)
  * [Allowing multiple clients](#allowing-multiple-clients)


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
- [S] : Sends the message : **REQUEST or DONE** indicating that the client can start sending:
- [C] : Sends the message : **REQUEST <Message>**
  - **REQUEST <Message>** : REQUEST indicates that the client want's the server to analyse the **Message** for language detection.  
  - **DONE** : Indicates that the client is done sending requests.
... Multiple **REQUEST** from the client and responses from the Server **(Maximum of 4 requests)**
- [C] : Sends the message : **DONE**
- [S] : Sends the message : **GOOD BYE - <Number of queries answered> queries answered**

## Server Socket

### Never ending loop

### Working with clients sockets

### Multi-threading

## Client class

### Extending Thread Class

### Allowing multiple clients