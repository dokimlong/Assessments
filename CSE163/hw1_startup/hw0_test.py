"""
Kim-Long Do
4/6/21
Take-Home Assessment # 0
TA: Zoe, Ryan

# This program tests hw0 functions
"""

import hw0

from cse163_utils import assert_equals


def test_funky_sum():
    """
    This function checks that that the funky_sum function in hw0 works
    properly by testing the inputs with an output.
    """
    assert_equals(2.0, hw0.funky_sum(1, 3, 0.5))
    assert_equals(2.2, hw0.funky_sum(1, 3, 0.6))
    assert_equals(2.0, hw0.funky_sum(1, 5, 0.25))
    assert_equals(5.0, hw0.funky_sum(2, 6, 0.75))


def test_total():
    """
    This function checks that that the total function in hw0 works
    properly by testing the input with an output.
    """
    # The regular case
    assert_equals(15, hw0.total(5))
    # Seems likely we could mess up 0 or 1
    assert_equals(1, hw0.total(1))
    assert_equals(0, hw0.total(0))
    # Tests the none case
    assert_equals(None, hw0.total(-1))


def test_swip_swap():
    """
    This function checks that that the swip_swap function in hw0 works
    properly by testing the inputs with an output.
    """
    assert_equals('offbar', hw0.swip_swap('foobar', 'f', 'o'))
    assert_equals('foocar', hw0.swip_swap('foobar', 'b', 'c'))
    assert_equals('brush', hw0.swip_swap('crush', 'b', 'c'))
    assert_equals('Msiisiispps', hw0.swip_swap('Mississippi', 'i', 's'))


def main():
    test_total()
    test_funky_sum()
    test_swip_swap()


if __name__ == '__main__':
    main()
