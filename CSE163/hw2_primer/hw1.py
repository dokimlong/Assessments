"""
Kim-Long Do
4/13/21
Take-Home Assessment # 1
TA: Zoe, Ryan

This program implements the functions for HW1 by using control structures
and data structures to solve problems
"""


def total(n):
    """
    Returns the sum of the numbers from 0 to n (inclusive).
    If n is negative, returns None.
    """
    if n < 0:
        return None
    else:
        result = 0
        for i in range(n + 1):
            result += i
        return result


def count_divisible_digits(n, m):
    """
    Returns the number of digits in 'n' that are divisible by 'm'.
    0 can be divisible by any number
    Returns 0 if m equals 0
    m is between 0(inclusive) and 10 (inclusive)
    n can be negative but digits are treated as non-negative
    """
    if m == 0:
        return 0
    count = 0
    n = abs(n)
    if n == 0:
        count += 1
    else:
        while n > 0:
            if n % 10 == 0:
                count += 1
            elif n % 10 % m == 0:
                count += 1
            n = n // 10
    return count


def is_relatively_prime(n, m):
    """
    Returns True if the integers 'n' and 'm' do not share any common factor
    besides 1
    Returns False when n and m are equal
    """
    if n == m:
        return False
    num_max = max(n, m)
    for i in range(2, num_max + 1):
        if n % i == 0 and m % i == 0:
            return False
    return True


def travel(moves, x, y):
    """
    This function takes in a string of 'moves' and a starting 'x' and 'y'
    coordinate.Moves represents the steps taken from the starting point
    to the endpoint as compass directions (first letter of north, east,
    south, and west).
    The lettercase of the moves doesn't matter.
    Returns the endpoint coordinates as a tuple
    """
    moves = moves.lower()
    for i in range(0, len(moves)):
        if moves[i] == 'n':
            y += 1
        elif moves[i] == 's':
            y += -1
        elif moves[i] == 'e':
            x += 1
        elif moves[i] == 'w':
            x += -1
    return (x, y)


def compress(word):
    """
    Returns a string from 'word' where each character is followed by the
    count of adjacent same characters.
    All adjacent duplicate characters are removed.
    """
    result = ''
    count = 0
    for i in range(0, len(word)):
        if i == 0:
            result += word[i]
            count += 1
        elif word[i] == word[i - 1]:
            count += 1
        else:
            result += str(count)
            result += word[i]
            count = 1
    if word != '':
        result += str(count)
    return result


def longest_word(text_file):
    """
    Returns the longest word and which line the word appears on from a string
    'text_file'.
    Returns None if the file is empty.
    """
    max_word = ''
    max_line = 0
    num_line = 0
    with open(text_file) as text:
        lines = text.readlines()
        if len(lines) > 0:
            for line in lines:
                num_line += 1
                words = line.split()
                for word in words:
                    if len(word) > len(max_word):
                        max_word = word
                        max_line = num_line
            return str(max_line) + ": " + max_word
        else:
            return None


def get_average_in_range(num_list, low, high):
    """
    This function takes in a list of numbers 'num_list' and two
    integers: low and high.
    Returns the average of the numbers from the list that appears between
    the numbers low (inclusive) and high(exclusive)
    Returns 0 if no numbers are in range
    """
    num_count = 0
    sum_range = 0
    average = 0.0
    for value in num_list:
        if (low <= value < high):
            num_count += 1
            sum_range += value
    if num_count != 0:
        average = sum_range / num_count
    return average


def mode_digit(n):
    """
    Returns the highest occurrence of a digit within an integer 'n'.
    Returns 0 if n equals 0
    Returns the highest digit if there is a tie in the highest occurrence
    for multiple digits.
    n can be negative but return value is non-negative.
    """
    count = [0]*10
    if n == 0:
        return 0
    elif n < 0:
        n = abs(n)
    while n > 0:
        last_digit = n % 10
        count[last_digit] += 1
        n = n // 10
    check_count = 0
    num_highest = 0
    for i in range(0, len(count)):
        if count[i] >= check_count:
            check_count = count[i]
            num_highest = i
    return num_highest
