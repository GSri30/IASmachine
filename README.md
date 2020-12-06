<h1 align="center">Welcome to IASmachine 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://twitter.com/SriHarshaG6" target="_blank">
    <img alt="Twitter: SriHarshaG6" src="https://img.shields.io/twitter/follow/SriHarshaG6.svg?style=social" />
  </a>
</p>

### The implementation has the following structure:

IASMachine  
    &nbsp;&nbsp;&nbsp;&nbsp;-ALU  
    &nbsp;&nbsp;&nbsp;&nbsp;-Memory  
    &nbsp;&nbsp;&nbsp;&nbsp;-ProgramControlUnit  

where the bits are handled by "BitsArray" (Uses strings to implement)\ 
It also has various methods to get certain range of bits, get the bits representaion padded with zeros in front etc.\
Other than the above mentioned 4 files, I have a main file, which takes in the input of assembly code from a text file("input.txt" is given as an example).\
"Colors" file is used to implement the colors on the bash. (during the execution).\
A few screenshots have also been included in the "screenshots" repository


ALU\
    &nbsp;&nbsp;-Containes few registers (MBR, AC, MQ)\
    &nbsp;&nbsp;-Containes various bit manipulations and arithematic operations. It has methods to compute\
        &nbsp;&nbsp;&nbsp;&nbsp;-Negative number(in binary) form of a given binary\
        &nbsp;&nbsp;&nbsp;&nbsp;-Mod of a binary\
        &nbsp;&nbsp;&nbsp;&nbsp;-addition\
        &nbsp;&nbsp;&nbsp;&nbsp;-subtraction\
        &nbsp;&nbsp;&nbsp;&nbsp;-multiplication\
        &nbsp;&nbsp;&nbsp;&nbsp;-division\
        &nbsp;&nbsp;&nbsp;&nbsp;-Left shift\
        &nbsp;&nbsp;&nbsp;&nbsp;-Right shift\
    &nbsp;&nbsp;-This class is used to handle all the arithematic operations.


Memory\
    &nbsp;&nbsp;-Has method to create a main Memory\
    &nbsp;&nbsp;-Each word is fixed as 40bits and is implemented using "BitsArray" data structure.(implemented seperately)\
    &nbsp;&nbsp;-Creates a 2D matrix of bits (or array of "BitsArray")


ProgramControlUnit\
    &nbsp;&nbsp;-It takes in ALU and Memory\
    &nbsp;&nbsp;-It has two sub classes\
        &nbsp;&nbsp;&nbsp;&nbsp;-registers (PC,MAR,IR,IBR)\
        &nbsp;&nbsp;&nbsp;&nbsp;-ISA (Has a hashmap of opcodes and their definitions) (Which are used to decode instructions)\
           &nbsp;&nbsp;&nbsp;&nbsp; (HALT is assigned with "000000000000" as its opcode)\
   &nbsp;&nbsp;-Has methods to fetch and decode the instructions from the main Memory


IASMachine\
    &nbsp;&nbsp;-Unites all the above classes to constitue an IAS computer\
    &nbsp;&nbsp;-Has method to execute it. (Fetch and decode till halt (or any error occurs))

## Usage

```sh
javac *.java
java IMT2019030_MAIN
```

## Author

👤 **Sri Harsha G**

* Website: https://gsri30.github.io/
* Twitter: [@SriHarshaG6](https://twitter.com/SriHarshaG6)
* Github: [@GSri30](https://github.com/GSri30)
* LinkedIn: [@https:\/\/www.linkedin.com\/in\/sri-harsha-g-15534a18a](https://linkedin.com/in/https:\/\/www.linkedin.com\/in\/sri-harsha-g-15534a18a)

## Show your support

Give a ⭐️ if this project helped you!
