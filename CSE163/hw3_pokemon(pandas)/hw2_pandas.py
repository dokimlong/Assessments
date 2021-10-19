"""
Kim-Long Do
4/13/21
Take-Home Assessment # 2
Pokemon
TA: Zoe, Ryan

This program create statistics on a file of pokemons through less_than_upper
Pandas package
"""


import pandas as pd


def species_count(data):
    """
    Returns the number of unique pokemons in the file
    """
    return len(pd.unique(data['name']))


def max_level(data):
    """
    Returns a tuple of a pokemon and its level, where the pokemon
    is the highest level within the file
    If there are multiple pokemons with the same highest level then the
    first one in the file is picked
    """
    return tuple(data.loc[data['level'].idxmax(), ['name', 'level']])


def filter_range(data, lower_bound, upper_bound):
    """
    Returns a list of pokemon names whose level falls between
    lower_bound(inclusive) and upper_bound(exclusive)
    """
    at_least_lower = data['level'] >= lower_bound
    less_than_upper = data['level'] < upper_bound
    in_range = list(data[at_least_lower & less_than_upper]['name'])
    return in_range


def mean_attack_for_type(data, mon_type):
    """
    Returns the average attack of a mon_type
    Returns None if there are no pokemon of mon_type within the file
    """
    is_type = data['type'] == mon_type
    data = data[is_type]
    if data.empty:
        return None
    else:
        avg_atk = data['atk'].mean()
        return avg_atk


def count_types(data):
    """
    Returns a dictionary of the number of pokemons for each type that appears
    within the file
    The order of the typing is based on the ones that appears first within
    the file
    """
    return dict(data.groupby('type')['type'].count())


def mean_attack_per_type(data):
    """
    Returns a dictionary of the average attack for each pokemon type that
    appears in the file
    The order of the typing is based on the ones that appears first within
    the file
    """
    return dict(data.groupby('type')['atk'].mean())
