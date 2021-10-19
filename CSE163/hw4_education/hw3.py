"""
Kim-Long Do
4/27/21
Take-Home Assessment # 3
Pokemon
TA: Zoe, Ryan

This program looks at a file about education levels within the last century
for various sexes and races. The program uses Pandas, Seaborn, and
scikit-learn to process, visualize, and predict outcomes from the data.
"""


import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeRegressor
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split


def compare_bachelors_1980(data):
    """
    Returns a 2x2 dataframe of the male and female total percentages with
    at leat a bachelor's in 1980
    """
    year_1980 = data['Year'] == 1980
    both_sexes = data['Sex'] != 'A'
    degree = data['Min degree'] == "bachelor's"
    filtered_data = data[year_1980 & both_sexes & degree]
    return filtered_data.loc[:, ['Sex', 'Total']]


def top_2_2000s(data, sex='A'):
    """
    Returns a series of the two highest minimum education percentages for
    a given sex
    The default sex value is 'A'
    """
    between_years = (data['Year'] >= 2000) & (data['Year'] <= 2010)
    target_sex = data['Sex'] == sex
    filtered_data = data[between_years & target_sex]
    return filtered_data.groupby('Min degree')['Total'].mean().nlargest(2)


def line_plot_bachelors(data):
    """
    Creates a line plot of the total percetanges of people with a bachelor's
    over time.
    """
    degree = data['Min degree'] == "bachelor's"
    target_sex = data['Sex'] == 'A'
    filtered = data[degree & target_sex]
    sns.relplot(x='Year', y='Total', data=filtered, kind='line')
    plt.title("Percentage Earning Bachelor's over Time")
    plt.xlabel('Year')
    plt.ylabel('Percentage')
    plt.savefig('line_plot_bachelors.png', bbox_inches='tight')


def bar_chart_high_school(data):
    """
    Creates a bar chart of the percentage of those with at least high
    school education based on sexes.
    """
    degree = data['Min degree'] == 'high school'
    year_2009 = data['Year'] == 2009
    filtered = data[degree & year_2009]
    sns.catplot(x='Sex', y='Total', data=filtered, kind='bar')
    plt.title('Percentage Completed High School by Sex')
    plt.xlabel('Sex')
    plt.ylabel('Percentage')
    plt.savefig('bar_chart_high_school.png', bbox_inches='tight')


def plot_hispanic_min_degree(data):
    """
    Creates a line plot of how degrees have changed from 1990-2010(inclusive)
    for the hispanic population for high school and bachelor's
    """
    between_yrs = (data['Year'] >= 1990) & (data['Year'] <= 2010)
    degree_bach = data['Min degree'] == "bachelor's"
    degree_high = data['Min degree'] == "high school"
    target_sex = data['Sex'] == 'A'
    filtered = data[between_yrs & (degree_bach | degree_high) & target_sex]
    sns.relplot(x='Year', y='Hispanic', hue='Min degree', data=filtered,
                kind='line')
    plt.title('Change in Percentage Min Degree for Hispanics over Time')
    plt.xlabel('Year')
    plt.xticks([1990, 1994, 1998, 2002, 2006, 2010], rotation=-45)
    plt.ylabel('Percentage')
    plt.savefig('plot_hispanic_min_degree.png', bbox_inches='tight')


def fit_and_predict_degrees(data):
    """
    This function predicts the percentage of Sex to earn a type of degree
    in a given year through regression.
    Returns the test mean squared value as a float
    """
    data = data[['Year', 'Min degree', 'Sex', 'Total']]
    data = data.dropna()
    features = data[['Min degree', 'Sex', 'Year']]
    features = pd.get_dummies(features)
    labels = data['Total']
    feat_train, feat_test, label_train, label_test = \
        train_test_split(features, labels, test_size=0.2)
    model = DecisionTreeRegressor()
    model.fit(feat_train, label_train)
    DecisionTreeRegressor()
    error = mean_squared_error(model.predict(feat_test), label_test)
    return error


def main():
    data = pd.read_csv('nces-ed-attainment.csv', na_values=['---'])
    sns.set()
    compare_bachelors_1980(data)
    top_2_2000s(data)
    line_plot_bachelors(data)
    bar_chart_high_school(data)
    plot_hispanic_min_degree(data)
    fit_and_predict_degrees(data)


if __name__ == '__main__':
    main()
