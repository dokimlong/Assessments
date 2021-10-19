"""
Kim-Long Do
5/11/21
Take-Home Assessment # 5
Mapping
TA: Zoe, Ryan

This program analyzes and plot geospatial data to study food deserts within
the State of Washington.
"""

import pandas as pd
import geopandas as gpd
import matplotlib.pyplot as plt


def load_in_data(census, food):
    """
    Takes in a census dataset in shapefile format and a food tabular dataset
    Returns a geoDataFrame of the census and food access dataset based on
    their census tract identifier.
    """
    shapes = gpd.read_file(census)
    data = pd.read_csv(food)
    merged_data = shapes.merge(data, left_on='CTIDFP00',
                               right_on='CensusTract', how='left')
    geo = gpd.GeoDataFrame(merged_data, geometry='geometry')
    return geo


def percentage_food_data(data):
    """
    Returns the percentage of the state's data with food access data as
    a float
    """
    num_na = len(data[data['CensusTract'].isna()])
    total_count = len(data)
    percentage = ((total_count - num_na) / total_count) * 100
    return percentage


def plot_map(data):
    """
    Maps the State of Washington
    """
    data.plot()
    plt.title('Washington State')
    plt.savefig('map.png')


def plot_population_map(data):
    """
    Maps the population of Washington with available data
    """
    fig, ax = plt.subplots(1)
    data.plot(color='#EEEEEE', ax=ax)
    data.plot(column='POP2010', legend=True, ax=ax)
    plt.title('Washington Census Tract Populations')
    plt.savefig('population_map.png')


def plot_population_county_map(data):
    """
    Maps the population of Washington State based on each county
    """
    tract = data[['County', 'POP2010', 'geometry']]
    tract = tract.dissolve(by='County', aggfunc='sum')
    fig, ax = plt.subplots(1)
    data.plot(color='#EEEEEE', ax=ax)
    tract.plot(column='POP2010', legend=True, ax=ax)
    plt.title('Washington County Populations')
    plt.savefig('county_population_map.png')


def plot_food_access_by_county(data):
    """
    Maps the food accessibility across Washington based on income levels
    and counties
    """
    tract = data[['County', 'POP2010', 'lapophalf', 'lapop10',
                  'lalowihalf', 'lalowi10', 'geometry']]
    tract = tract.dissolve(by='County', aggfunc='sum')
    tract['lapophalf_ratio'] = tract['lapophalf'] / tract['POP2010']
    tract['lalowihalf_ratio'] = tract['lalowihalf'] / tract['POP2010']
    tract['lapop10_ratio'] = tract['lapop10'] / tract['POP2010']
    tract['lalowi10_ratio'] = tract['lalowi10'] / tract['POP2010']
    fig, [[ax1, ax2], [ax3, ax4]] = plt.subplots(2, 2, figsize=(20, 10))
    data.plot(color='#EEEEEE', ax=ax1)
    data.plot(color='#EEEEEE', ax=ax2)
    data.plot(color='#EEEEEE', ax=ax3)
    data.plot(color='#EEEEEE', ax=ax4)
    tract.plot(column='lapophalf_ratio', legend=True, ax=ax1, vmin=0, vmax=1)
    tract.plot(column='lalowihalf_ratio', legend=True, ax=ax2, vmin=0, vmax=1)
    tract.plot(column='lapop10_ratio', legend=True, ax=ax3, vmin=0, vmax=1)
    tract.plot(column='lalowi10_ratio', legend=True, ax=ax4, vmin=0, vmax=1)
    ax1.set_title('Low Access: Half')
    ax2.set_title('Low Access + Low Income: Half')
    ax3.set_title('Low Access: 10')
    ax4.set_title('Low Access + Low Income: 10')
    plt.savefig('county_food_access.png')


def plot_low_access_tracts(data):
    """
    Maps a plot of low urban and rural food access tracts across Washington
    Urban low access starts at half a mile or more for urban regions
    Rural low access starts at 10 miles or more for rural regions
    """
    has_data = data.dropna().copy()
    has_data['comb_low'] = has_data['lapophalf'] + has_data['lapop10']
    urb_cond1 = has_data['Urban'] == 1
    urb_cond2 = (has_data['comb_low'] >= 500) | (has_data['comb_low'] >=
                                                 has_data['POP2010']/3)
    rur_cond1 = has_data['Rural'] == 1
    rur_cond2 = (has_data['lapop10'] >= 500) | (has_data['lapop10'] >=
                                                has_data['POP2010']/3)
    fig, ax = plt.subplots(1)
    data.plot(color='#EEEEEE', ax=ax)
    has_data.plot(color='#AAAAAA', ax=ax)
    has_data[(urb_cond1 & urb_cond2) | (rur_cond1 & rur_cond2)].plot(ax=ax)
    plt.title('Low Access Census Tracts')
    plt.savefig('low_access')


def main():
    state_data = load_in_data(
        '/course/food_access/tl_2010_53_tract00/tl_2010_53_tract00.shp',
        '/course/food_access/food_access.csv'
    )
    print(percentage_food_data(state_data))
    plot_map(state_data)
    plot_population_map(state_data)
    plot_population_county_map(state_data)
    plot_food_access_by_county(state_data)
    plot_low_access_tracts(state_data)


if __name__ == '__main__':
    main()
