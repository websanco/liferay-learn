// TODO

import React, {useEffect, useState} from 'react'

import FusionCharts from 'fusioncharts'
import Charts from 'fusioncharts/fusioncharts.charts'
import ReactFC from 'react-fusioncharts'
import FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion'

import LiferayApi from '../common/services/liferay/api'

ReactFC.fcRoot(FusionCharts, Charts, FusionTheme)

const chartConfigs = {
	dataFormat: 'json',
	dataSource: {},
	height: 400,
	type: 'column2d',
	width: 600
}

const Chart = () => {
	const [state, setState] = useState(chartConfigs)

	const getChartData = data => {
		let chartData = []

		for (let i in data) {
			let obj = {}
			obj.value = data[i].value
			obj.label = data[i].label
			chartData.push(obj)
		}

		return chartData
	}

	const getDataFromObjects = async () => {
		try {
			const result = await LiferayApi('o/c/x3j8objects/')

			setState({
				dataSource: {
					chart: {
						caption: 'Impactful Data from Specific Areas',
						numberSuffix: 'M',
						subCaption: 'In OMDP = One Million Data Points',
						theme: 'fusion',
						updateAnimDuration: '0.3',
						xAxisName: 'Area',
						yAxisName: 'Data Points (OMDP)'
					},
					data: getChartData(result.data.items)
				}
			})
		} catch (error) {
			console.log(error.message)
		}
	}

	useEffect(() => {
		setInterval(() => {
			getDataFromObjects()
		}, 5000)

		getDataFromObjects()
	}, [])

	return <ReactFC {...state} />
}

export default Chart
