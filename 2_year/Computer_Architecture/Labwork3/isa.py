import json
from enum import Enum


class ALUOpcode(str, Enum):
    INC_A = "inc_a"
    INC_B = "inc_b"
    DEC_A = "dec_a"
    DEC_B = "dec_b"
    ADD = "add"
    SUB = "sub"
    MOD = "mod"
    MUL = "mul"
    DIV = "div"
    TEST = "test"
    SKIP_A = "skip_a"
    SKIP_B = "skip_b"

    def __str__(self) -> str:
        return str(self.value)


class Opcode(str, Enum):
    NOP = "nop"
    HLT = "hlt"

    INC = "inc"
    DEC = "dec"
    ADD = "add"
    SUB = "sub"
    MUL = "mul"
    DIV = "div"
    MOD = "mod"
    MOV = "mov"
    TEST = "test"

    JG = "jg"
    JNG = "jng"
    JZ = "jz"
    JNZ = "jnz"
    JMP = "jmp"

    def __str__(self) -> str:
        return str(self.value)


op3 = [Opcode.JG, Opcode.JZ, Opcode.JNZ, Opcode.JNG, Opcode.JMP]

op2 = [Opcode.ADD, Opcode.SUB, Opcode.MUL, Opcode.DIV, Opcode.MOD, Opcode.TEST, Opcode.MOV]

op1 = [Opcode.INC, Opcode.DEC]

op0 = [Opcode.NOP, Opcode.HLT]


class Selectors(str, Enum):
    FROM_R0_TO_ALU_A = "from_r0_to_alu_a"
    FROM_R1_TO_ALU_A = "from_r1_to_alu_a"
    FROM_R2_TO_ALU_A = "from_r2_to_alu_a"
    FROM_R3_TO_ALU_A = "from_r3_to_alu_a"
    FROM_PC_TO_ALU_A = "from_pc_to_alu_a"

    FROM_R0_TO_ALU_B = "from_r0_to_alu_b"
    FROM_R1_TO_ALU_B = "from_r1_to_alu_b"
    FROM_R2_TO_ALU_B = "from_r2_to_alu_b"
    FROM_R3_TO_ALU_B = "from_r3_to_alu_b"
    FROM_DR_TO_ALU_B = "from_dr_to_alu_b"
    FROM_IR_TO_ALU_B = "from_ir_to_alu_b"

    FROM_ALU_TO_R0 = "from_alu_to_r0"
    FROM_MEMORY_TO_R0 = "from_memory_to_r0"

    FROM_ALU_TO_R1 = "from_alu_to_r1"
    FROM_MEMORY_TO_R1 = "from_memory_to_r1"

    FROM_ALU_TO_R2 = "from_alu_to_r2"
    FROM_MEMORY_TO_R2 = "from_memory_to_r2"

    FROM_ALU_TO_R3 = "from_alu_to_r3"
    FROM_MEMORY_TO_R3 = "from_memory_to_r3"

    FROM_ADDR1_TO_AR = "from_addr1_to_ar"
    FROM_ADDR2_TO_AR = "from_addr2_to_ar"

    FROM_R0_TO_MEMORY = "from_r0_to_memory"
    FROM_R1_TO_MEMORY = "from_r1_to_memory"
    FROM_R2_TO_MEMORY = "from_r2_to_memory"
    FROM_R3_TO_MEMORY = "from_r3_to_memory"

    def __str__(self) -> str:
        return str(self.value)


def write_code(filename: str, code):
    with open(filename, "w", encoding="utf-8") as file:
        buf = []
        for instr in code:
            buf.append(json.dumps(instr))
        file.write("[" + ",\n ".join(buf) + "]")


def read_code(filename: str):
    with open(filename, encoding="utf-8") as file:
        return json.loads(file.read())
