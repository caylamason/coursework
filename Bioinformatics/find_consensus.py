# list of lists
def find_consensus_v1(frequency_matrix):
    base2index = {'A': 0, 'C': 1, 'G': 2, 'T': 3}
    consensus = ''
    dna_length = len(frequency_matrix[0])

    for i in range(dna_length):  # loop over positions in string
        max_freq = -1            # holds the max freq. for this i
        max_freq_base = None     # holds the corresponding base

        for base in 'ATGC':
            if frequency_matrix[base2index[base]][i] > max_freq:
                max_freq = frequency_matrix[base2index[base]][i]
                max_freq_base = base
            elif frequency_matrix[base2index[base]][i] == max_freq:
                max_freq_base = '-' # more than one base as max

        consensus += max_freq_base  # add new base with max freq
    return consensus

# dict of dicts
def find_consensus_v3(frequency_matrix):
    consensus = ''
    dna_length = len(frequency_matrix['A']) # should be 5

    for i in range(dna_length):  # loop over positions in string
        max_freq = -1            # holds the max freq. for this i
        max_freq_base = None     # holds the corresponding base

        for base in 'ACGT':
            if frequency_matrix[base][i] > max_freq: # frequency_matrix['A'][i]
                max_freq = frequency_matrix[base][i]
                max_freq_base = base
            elif frequency_matrix[base][i] == max_freq:
                max_freq_base = '-' # more than one base as max

        consensus += max_freq_base  # add new base with max freq
    return consensus

def find_consensus(frequency_matrix):
    # check if dict of dict
    if isinstance(frequency_matrix, dict) and isinstance(frequency_matrix['A'], dict):
        # call find_consensus_v3
        return find_consensus_v3(frequency_matrix)

    # check if list of lists
    elif isinstance(frequency_matrix, list) and isinstance(frequency_matrix[0], list):
        # call find_consensus_v1
        return find_consensus_v1(frequency_matrix)

    else:
        print('frequency_matrix must be list of lists OR'
                        ' dictionary of dictionaries.')

# positive controls
dict_of_dicts = {'A': {0: 0, 1: 0, 2: 0, 3: 2, 4: 0},
                 'C': {0: 0, 1: 0, 2: 0, 3: 0, 4: 2},
                 'G': {0: 3, 1: 3, 2: 0, 3: 1, 4: 1},
                 'T': {0: 0, 1: 0, 2: 3, 3: 0, 4: 0}}

list_of_lists = [[0, 0, 0, 2, 0],
                 [0, 0, 0, 0, 2],
                 [3, 3, 0, 1, 1],
                 [0, 0, 3, 0, 0]]

# negative control
dict_of_lists = {'A': [0, 0, 0, 2, 0],
                 'C': [0, 0, 0, 0, 2],
                 'G': [3, 3, 0, 1, 1],
                 'T': [0, 0, 3, 0, 0]}

print(find_consensus(dict_of_lists))

