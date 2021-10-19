"""
Kim-Long Do
4/13/21
Take-Home Assessment # 1
TA: Zoe, Ryan

# This program tests hw1 functions
"""

import hw1

from cse163_utils import assert_equals


def test_total():
    """
    Tests the total method
    """
    # The regular case
    assert_equals(15, hw1.total(5))
    # Seems likely we could mess up 0 or 1
    assert_equals(1, hw1.total(1))
    assert_equals(0, hw1.total(0))

    # Test the None case
    assert_equals(None, hw1.total(-1))


def test_count_divisible_digits():
    """
    Tests the count_divisible_digits method
    """
    assert_equals(4, hw1.count_divisible_digits(650899, 3))
    assert_equals(1, hw1.count_divisible_digits(-204, 5))
    assert_equals(0, hw1.count_divisible_digits(24, 5))
    assert_equals(0, hw1.count_divisible_digits(1, 0))
    assert_equals(0, hw1.count_divisible_digits(81, 3))
    assert_equals(1, hw1.count_divisible_digits(1776, 3))


def test_is_relatively_prime():
    """
    Tests the is_relatively_prime method
    """
    assert_equals(True, hw1.is_relatively_prime(12, 13))
    assert_equals(False, hw1.is_relatively_prime(12, 14))
    assert_equals(True, hw1.is_relatively_prime(5, 9))
    assert_equals(True, hw1.is_relatively_prime(8, 9))
    assert_equals(True, hw1.is_relatively_prime(8, 1))
    assert_equals(False, hw1.is_relatively_prime(12, 42))
    assert_equals(False, hw1.is_relatively_prime(12, 174))


def test_travel():
    """
    Tests the travel method
    """
    assert_equals((-1, 4), hw1.travel('NW!ewnW', 1, 2))
    assert_equals((2, 5), hw1.travel('NNeNWNNswt', 3, 1))
    assert_equals((3, 3), hw1.travel('WeeNeo', 1, 2))


def test_compress():
    """
    Tests the compress method
    """
    assert_equals('c1o17l1k1a1n1g1a1r1o2',
                  hw1.compress('cooooooooooooooooolkangaroo'))
    assert_equals('a3', hw1.compress('aaa'))
    assert_equals('', hw1.compress(''))
    assert_equals('b1o2k1', hw1.compress('book'))
    assert_equals('h1i1s5i1n2g1', hw1.compress('hisssssinng'))


def test_longest_word():
    """
    Tests the longest_word method
    """
    assert_equals('3: Merrily,', hw1.longest_word('/home/song.txt'))
    assert_equals('2: script', hw1.longest_word('/home/not_bee_movie.txt'))
    assert_equals('3: Merrily,', hw1.longest_word('/home/song.txt'))
    assert_equals('5: Einstein.', hw1.longest_word('/home/short_story.txt'))


def test_get_average_in_range():
    """
    Tests the get_average_in_range method
    """
    assert_equals(5.5, hw1.get_average_in_range([1, 5, 6, 7, 9], 5, 7))
    assert_equals(2.0, hw1.get_average_in_range([1, 2, 3], -1, 10))
    assert_equals(7.0, hw1.get_average_in_range([4, 6, 8, 10], 6, 10))
    assert_equals(8.0, hw1.get_average_in_range([3, 5, 9, 10, 11], 5, 11))


def test_mode_digit():
    """
    Tests the mode_digit method
    """
    assert_equals(1, hw1.mode_digit(12121))
    assert_equals(0, hw1.mode_digit(0))
    assert_equals(2, hw1.mode_digit(-122))
    assert_equals(2, hw1.mode_digit(1211232231))
    assert_equals(5, hw1.mode_digit(5554402294))
    assert_equals(2, hw1.mode_digit(2922889922))


def main():
    test_total()
    test_count_divisible_digits()
    test_is_relatively_prime()
    test_travel()
    test_compress()
    test_longest_word()
    test_get_average_in_range()
    test_mode_digit()


if __name__ == '__main__':
    main()
