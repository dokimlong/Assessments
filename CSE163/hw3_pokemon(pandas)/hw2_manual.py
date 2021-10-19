"""
Kim-Long Do
4/13/21
Take-Home Assessment # 2
Pokemon
TA: Zoe, Ryan

This program create statistics on a file of pokemons through control and
data structures.
"""


def species_count(data):
    """
    Returns the number of unique pokemons in the file
    """
    return len(set(species['name'] for species in data))


def max_level(data):
    """
    Returns a tuple of a pokemon and its level, where the pokemon
    is the highest level within the file
    If there are multiple pokemons with the same highest level then the
    first one in the file is picked
    """
    max_lvl = 0
    mon_name = ''
    for row in data:
        if row['level'] > max_lvl:
            max_lvl = row['level']
            mon_name = row['name']
    return (mon_name, max_lvl)


def filter_range(data, lower_bound, upper_bound):
    """
    Returns a list of pokemon names whose level falls between
    lower_bound(inclusive) and upper_bound(exclusive)
    """
    mon_names = []
    for row in data:
        if lower_bound <= row['level'] < upper_bound:
            mon_names.append(row['name'])
    return mon_names


def mean_attack_for_type(data, mon_type):
    """
    Returns the average attack of a mon_type
    Returns None if there are no pokemon of mon_type within the file
    """
    num_mon = 0
    atk_sum = 0
    for row in data:
        if row['type'] == mon_type:
            num_mon += 1
            atk_sum += row['atk']
    if num_mon == 0:
        return None
    mean_atk = atk_sum / num_mon
    return mean_atk


def count_types(data):
    """
    Returns a dictionary of the number of pokemons for each type that appears
    within the file
    The order of the typing is based on the ones that appears first within
    the file
    """
    count_types = {}
    for mon_typing in data:
        typing = mon_typing['type']
        if typing not in count_types:
            count_types[typing] = 1
        else:
            count_types[typing] += 1
    return count_types


def mean_attack_per_type(data):
    """
    Returns a dictionary of the average attack for each pokemon type that
    appears in the file
    The order of the typing is based on the ones that appears first within
    the file
    """
    count_types = {}
    count_atk = {}
    for mon_typing in data:
        typing = mon_typing['type']
        mon_atk = mon_typing['atk']
        if typing not in count_types:
            count_types[typing] = 1
            count_atk[typing] = mon_atk
        else:
            count_types[typing] += 1
            count_atk[typing] += mon_atk
    avg_type_atk = {i: count_atk[i] / count_types[i] for i in count_atk}
    return avg_type_atk
