// #include <stdio.h>

#include <elf.h>
#include <errno.h>
#include <fcntl.h>
#include <stdbool.h>
#include <stdint.h>
#include <unistd.h>


int main( int argc, char** argv ) {
    (void) argc; (void) argv; // supress 'unused parameters' warning
// 1. Прописать проверку того, сколько аргументов передаётся программе
    if(argc != 3) {
        return EINVAL;
    }
    // 2. Попытаться открыть файл. В случае неудачи вывести ошибку
        const int elf_file_fd = open(argv[1], O_RDONLY);
    if(elf_file_fd == -1) {
        return errno;
    }

    // 3. Чтение файла и сравнение раззмера прочитанного файла с размером заголовка ELF файла
    Elf64_Ehdr elf_header;

    // 4. Проверка на то, правильный ли e_ident находится в заголовке
    // 5.
    return 0;
}

