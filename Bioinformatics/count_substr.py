# PH700A
# Cayla Mason

def count_substring(sub_string, main_string):

    count = 0
    for index in range(len(main_string)-len(sub_string)):
        if main_string[index] == sub_string[0]:
            if sub_string == main_string[index : index + len(sub_string)]:
                count += 1
        else:
            continue

    print(count)


# count_substring("ABC","SIGDKDABCKKSPSNABCPLWE")