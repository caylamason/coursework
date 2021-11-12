# PH700A
# Cayla Mason

def get_base_counts2(dna):
    counts = {'A': 0, 'T': 0, 'G': 0, 'C': 0}
    for base in dna:
        try:
            counts[base] += 1
        except:
            continue
    return counts

# count_dict = get_base_counts2('ATGCTCGAA')
# for key, value in count_dict.items():
#     print(key, "\t", value)
#
# count_dict = get_base_counts2('ADLSTTLLD')
# for key, value in count_dict.items():
#     print(key, "\t", value)