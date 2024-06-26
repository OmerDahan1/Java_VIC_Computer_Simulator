# Java_VIC_Computer_Simulator
Java-based VIC computer simulator for executing programs written in VIC machine language, featuring components like memory management, register operations, and program execution simulation.

## Overview

The VIC Computer Simulator project consists of several Java classes designed to mimic the behavior of a VIC computer and its components:

- **Computer**: Represents the core VIC computer with memory, registers, and execution capabilities for VIC machine language programs.
- **Memory**: Simulates the RAM unit of the VIC computer, allowing read and write operations to memory addresses.
- **Register**: Represents a single register in the VIC computer, used for storing integer values and supporting basic operations.

## Features

- **Computer Class**:
  - Executes VIC programs stored in memory.
  - Supports loading programs and initializing input for execution.
  - Provides methods for resetting, running programs, and displaying state.

- **Memory Class**:
  - Manages an array of registers to simulate memory.
  - Implements methods for setting and getting values at specific memory addresses.
  - Includes a reset method to clear memory contents.

- **Register Class**:
  - Represents a register with methods to set value, increment value, and retrieve current value.
  - Initializes registers with default values and provides string representation of register value.

- **VicSimulator Class**:
  - Main driver class to execute the VIC computer simulation.
  - Loads VIC program and optional input file from command line arguments.
  - Displays final state of the VIC computer after program execution.

## Usage

1. **Setup**:
   - Ensure Java Development Kit (JDK) is installed on your system.
   - Compile all `.java` files in the project directory.
   - The code of `StdIn.java` is available [here](https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java), place it in the same directory as the Java files.


2. **Execution**:
   - Run `java VicSimulator <program_file> [input_file]` to simulate VIC program execution.
   - Replace `<program_file>` with the path to your VIC program file.
   - `[input_file]` is optional and can provide input during program execution.

3. **Testing**:
   - Run `java Test` to execute basic unit tests for VIC computer components.
   - Modify `Test.java` to expand or customize tests for your requirements.

## Example

To run a sample VIC program:

```bash
java VicSimulator program1.vic input2.txt
