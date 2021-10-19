"""
Kim-Long Do
4/6/21
Take-Home Assessment # 0
TA: Zoe, Ryan

This program implements the functions for HW0
"""


def funky_sum(a, b, mix):
    """
    This function takes in the numbers a, b, and mix.
    Returns a value using a and b, using mix as a ratio from 0 to 1 between
    the two numbers.
    0 represents a and 1 represents b.
    Returns a if mix is less than or equal to 0
    Returns b if mix is greater than other equal to 1
    """
    if mix <= 0:
        return a
    elif mix >= 1:
        return b
    else:
        return (1 - mix) * a + mix * b


def total(n):
    """
    This function takes in a number 'n' and returns the sum of every whole
    number from 0 to n.
    Returns None if n is less than 0.
    """
    if n < 0:
        return None
    else:
        result = 0
        for i in range(n + 1):
            result += i
        return result


def swip_swap(source, c1, c2):
    """
    This function takes in the strings source, c1, and c2, and returns source
    with every instance of c1 and c2 switched with each other within source.
    """
    result = ''
    for i in range(len(source)):
        if source[i] == c1:
            result += c2
        elif source[i] == c2:
            result += c1
        else:
            result += source[i]
    return result
