# The implementation has the following structure:

IASMachine
    -ALU
    -Memory
    -ProgramControlUnit

where the bits are handled by "BitsArray" (Uses strings to implement). 
It also has various methods to get certain range of bits, get the bits representaion padded with zeros in front etc.
Other than the above mentioned 4 files, I have a main file, which takes in the input of assembly code from a text file("input.txt" is given as an example).
"Colors" file is used to implement the colors on the bash. (during the execution).
A few screenshots have also been included in the "screenshots" repository


ALU
    -Containes few registers (MBR, AC, MQ)
    -Containes various bit manipulations and arithematic operations. It has methods to compute
        -Negative number(in binary) form of a given binary
        -Mod of a binary
        -addition
        -subtraction
        -multiplication
        -division
        -Left shift
        -Right shift
    -This class is used to handle all the arithematic operations.


Memory
    -Has method to create a main Memory
    -Each word is fixed as 40bits and is implemented using "BitsArray" data structure.(implemented seperately)
    -Creates a 2D matrix of bits (or array of "BitsArray")


ProgramControlUnit
    -It takes in ALU and Memory 
    -It has two sub classes
        -registers (PC,MAR,IR,IBR)
        -ISA (Has a hashmap of opcodes and their definitions) (Which are used to decode instructions)
            (HALT is assigned with "000000000000" as its opcode)

    -Has methods to fetch and decode the instructions from the main Memory


IASMachine
    -Unites all the above classes to constitue an IAS computer
    -Has method to execute it. (Fetch and decode till halt (or any error occurs))