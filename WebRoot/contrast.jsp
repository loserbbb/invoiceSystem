<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>进销项对比分析</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="scripts/eCharts3/echarts.min.js"></script>
	<script type="text/javascript" src="scripts/ajaxutils/ajaxutils.js"></script>
  </head>
  
  <body>
     <h1 align="center">进销项数据对比分析</h1>
   <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
   <div id="main" style="width: 90%;height:300px;"></div>
   <div id="main1" style="width: 90%;height:300px;"></div>
   <div id="main2" style="width: 90%;height:300px;"></div>
   <div id="main3" style="width: 90%;height:300px;"></div>
   <div id="main4" style="width: 90%;height:300px;"></div>
<script type="text/javascript">
 window.onload = function() {
	/*
	1. ajax--进销项税额季度对比
	*/
	ajax(
		{
			url:"<c:url value='/ContrastServlet?method=getQuarterData'/>",
			type:"json",
			callback:function(data) {
				quarterTaxChart(data);
			}
		}
	);
	/*
	1. ajax--进销项税额月度对比
	*/
	ajax(
			{
				url:"<c:url value='/ContrastServlet?method=getMonthData'/>",
				type:"json",
				callback:function(data) {
					monthTaxChart(data);
				}
			}
		);
	/*
	1. ajax--进销项税额周度对比
	*/
	ajax(
			{
				url:"<c:url value='/ContrastServlet?method=getWeekData'/>",
				type:"json",
				callback:function(data) {
					weekTaxChart(data);
				}
			}
		);
 };
 function quarterTaxChart(data1){
	    // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('main'));

	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: '进销项税额季度对比分析'
	        },
	        tooltip: {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'cross',
	                crossStyle: {
	                    color: '#999'
	                }
	            }
	        },
	        legend: {
	            data:[{
	                name: '进项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            },{
	                name: '进项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            }]
	        },
	        xAxis: {
	            data: data1.keys
	        },
	        yAxis: {},
	      //可放大缩小
	        dataZoom: [{
	            startValue: data1.keys[0]
	        }, {
	            type: 'inside'
	        }],
	        series: [{
	            name: '进项金额-柱形',
	            type: 'bar',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-柱形',
	            type: 'bar',
	            data: data1.outvalues
	        },{
	            name: '进项金额-折线',
	            type: 'line',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-折线',
	            type: 'line',
	            data: data1.outvalues
	        }]
	    };

	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	}
 function monthTaxChart(data1){
	    // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('main1'));

	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: '进销项税额月度对比分析'
	        },
	        tooltip: {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'cross',
	                crossStyle: {
	                    color: '#999'
	                }
	            }
	        },
	        legend: {
	            data:[{
	                name: '进项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            },{
	                name: '进项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            }]
	        },
	        xAxis: {
	            data: data1.keys
	        },
	        yAxis: {},
	      //可放大缩小
	        dataZoom: [{
	            startValue: data1.keys[0]
	        }, {
	            type: 'inside'
	        }],
	        series: [{
	            name: '进项金额-柱形',
	            type: 'bar',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-柱形',
	            type: 'bar',
	            data: data1.outvalues
	        },{
	            name: '进项金额-折线',
	            type: 'line',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-折线',
	            type: 'line',
	            data: data1.outvalues
	        }]
	    };

	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	}
 function weekTaxChart(data1){
	    // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('main2'));

	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: '进销项税额周度对比分析'
	        },
	        tooltip: {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'cross',
	                crossStyle: {
	                    color: '#999'
	                }
	            }
	        },
	        legend: {
	            data:[{
	                name: '进项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-柱形',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            },{
	                name: '进项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为红色
	                textStyle: {
	                    color: 'red'
	                }
	            },{
	                name: '销项金额-折线',
	                // 强制设置图形为圆。
	                //icon: 'circle',
	                // 设置文本为蓝色
	                textStyle: {
	                    color: 'blue'
	                }
	            }]
	        },
	        xAxis: {
	            data: data1.keys
	        },
	        yAxis: {},
	      //可放大缩小
	        dataZoom: [{
	            startValue: data1.keys[0]
	        }, {
	            type: 'inside'
	        }],
	        series: [{
	            name: '进项金额-柱形',
	            type: 'bar',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-柱形',
	            type: 'bar',
	            data: data1.outvalues
	        },{
	            name: '进项金额-折线',
	            type: 'line',
	            data: data1.invalues
	        },
	        {
	            name: '销项金额-折线',
	            type: 'line',
	            data: data1.outvalues
	        }]
	    };

	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	}
 </script>
  </body>
</html>
