"""
Kim-Long Do
4/13/21
Take-Home Assessment # 2
Pokemon
TA: Zoe, Ryan

This program tests the functions of hw2_manual and hw2_pandas
"""


import pandas as pd

from cse163_utils import assert_equals, parse

import hw2_manual
import hw2_pandas


def test_species_count(dict1, df1, dict2, df2):
    """
    tests that the species_count method is working
    """
    assert_equals(3, hw2_manual.species_count(dict1))
    assert_equals(3, hw2_pandas.species_count(df1))
    assert_equals(5, hw2_manual.species_count(dict2))
    assert_equals(5, hw2_pandas.species_count(df2))


def test_max_level(dict1, df1, dict2, df2):
    """
    tests that the max_level method is working
    """
    assert_equals(('Lapras', 72), hw2_manual.max_level(dict1))
    assert_equals(('Lapras', 72), hw2_pandas.max_level(df1))
    assert_equals(('Jynx', 90), hw2_manual.max_level(dict2))
    assert_equals(('Jynx', 90), hw2_pandas.max_level(df2))


def test_filter_range(dict1, df1, dict2, df2):
    """
    tests that the filter_range method is working
    """
    assert_equals(['Arcanine', 'Arcanine', 'Starmie'],
                  hw2_manual.filter_range(dict1, 35, 72))
    assert_equals(['Arcanine', 'Arcanine', 'Starmie'],
                  hw2_pandas.filter_range(df1, 35, 72))
    assert_equals(['Nidoran(F)', 'Alakazam'],
                  hw2_manual.filter_range(dict2, 50, 71))
    assert_equals(['Nidoran(F)', 'Alakazam'],
                  hw2_pandas.filter_range(df2, 50, 71))


def test_mean_attack_for_type(dict1, df1, dict2, df2):
    """
    tests that the mean_attack_for_type method is working
    """
    assert_equals(47.5, hw2_manual.mean_attack_for_type(dict1, 'fire'))
    assert_equals(47.5, hw2_pandas.mean_attack_for_type(df1, 'fire'))
    assert_equals(82, hw2_manual.mean_attack_for_type(dict2, 'water'))
    assert_equals(82, hw2_pandas.mean_attack_for_type(df2, 'water'))
    assert_equals(None, hw2_manual.mean_attack_for_type(dict2, 'dragon'))
    assert_equals(None, hw2_pandas.mean_attack_for_type(df2, 'dragon'))


def test_count_types(dict1, df1, dict2, df2):
    """
    tests that the count_types method is working
    """
    assert_equals({'fire': 2, 'water': 2}, hw2_manual.count_types(dict1))
    assert_equals({'fire': 2, 'water': 2}, hw2_pandas.count_types(df1))
    assert_equals({'poison': 1, 'water': 2, 'psychic': 2, 'grass': 1},
                  hw2_manual.count_types(dict2))
    assert_equals({'poison': 1, 'water': 2, 'psychic': 2, 'grass': 1},
                  hw2_pandas.count_types(df2))


def test_mean_attack_per_type(dict1, df1, dict2, df2):
    """
    tests that the mean_attack_per_type method is working
    """
    assert_equals({'fire': 47.5, 'water': 140.5},
                  hw2_manual.mean_attack_per_type(dict1))
    assert_equals({'fire': 47.5, 'water': 140.5},
                  hw2_pandas.mean_attack_per_type(df1))
    assert_equals({'poison': 125, 'water': 82, 'psychic': 97, 'grass': 35},
                  hw2_manual.mean_attack_per_type(dict2))
    assert_equals({'poison': 125, 'water': 82, 'psychic': 97, 'grass': 35},
                  hw2_pandas.mean_attack_per_type(df2))


def main():
    dict1 = parse('/home/pokemon_test.csv')
    df1 = pd.read_csv('/home/pokemon_test.csv')
    dict2 = parse('/home/pokemon_test_pt2.csv')
    df2 = pd.read_csv('/home/pokemon_test_pt2.csv')
    test_species_count(dict1, df1, dict2, df2)
    test_max_level(dict1, df1, dict2, df2)
    test_filter_range(dict1, df1, dict2, df2)
    test_mean_attack_for_type(dict1, df1, dict2, df2)
    test_count_types(dict1, df1, dict2, df2)
    test_mean_attack_per_type(dict1, df1, dict2, df2)


if __name__ == '__main__':
    main()
