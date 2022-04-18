package com.tnh.testchart

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.NameValueDataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.*
import com.anychart.graphics.vector.Stroke
import com.anychart.scales.OrdinalColor
import com.tnh.testchart.models.ChartData


object DataSource {
    fun getData(): List<ChartData>{
        val list = mutableListOf<ChartData>()
        list.add(getPieChart())
        list.add(getColumnChart())
        list.add(getLineChart())
        list.add(getVennChart())
        list.add(getRadarChart())
        list.add(getTagCloud())

        return list
    }

    fun getPieChart(): ChartData{
        val pie = AnyChart.pie()
        val data = mutableListOf<ValueDataEntry>()
        data.add(ValueDataEntry("A", 10))
        data.add(ValueDataEntry("B", 20))
        data.add(ValueDataEntry("C", 40))
        data.add(ValueDataEntry("D", 40))

        pie.data(data.toList())
        pie.title("Test pie chart")
        pie.labels().position("outside")
        pie.legend().title().enabled(true)
        pie.legend().title("Retail channels")
        pie.legend().position("center-bottom").itemsLayout(LegendLayout.HORIZONTAL).align(Align.CENTER)

        return ChartData(
            "Pie chart",
            "https://www.w3schools.com/python/img_matplotlib_pie1.png",
            pie
        )
    }

    fun getRadarChart(): ChartData{
        val radar = AnyChart.radar()

        radar.title("WoW base stats comparison radar chart: Shaman vs Warrior vs Priest")

        radar.yScale().minimum(0.0)
        radar.yScale().minimumGap(0.0)
        radar.yScale().ticks().interval(50.0)

        radar.xAxis().labels().padding(5.0, 5.0, 5.0, 5.0)

        radar.legend()
            .align(Align.CENTER)
            .enabled(true)

        val data: MutableList<DataEntry> = ArrayList()
        data.add(CustomDataEntry("Strength", 136, 199, 43))
        data.add(CustomDataEntry("Agility", 79, 125, 56))
        data.add(CustomDataEntry("Stamina", 149, 173, 101))
        data.add(CustomDataEntry("Intellect", 135, 33, 202))
        data.add(CustomDataEntry("Spirit", 158, 64, 196))

        val set = Set.instantiate()
        set.data(data)
        val shamanData = set.mapAs("{ x: 'x', value: 'value' }")
        val warriorData = set.mapAs("{ x: 'x', value: 'value2' }")
        val priestData = set.mapAs("{ x: 'x', value: 'value3' }")

        val shamanLine = radar.line(shamanData)
        shamanLine.name("Shaman")
        shamanLine.markers()
            .enabled(true)
            .type(MarkerType.CIRCLE)
            .size(3.0)

        val warriorLine = radar.line(warriorData)
        warriorLine.name("Warrior")
        warriorLine.markers()
            .enabled(true)
            .type(MarkerType.CIRCLE)
            .size(3.0)

        val priestLine = radar.line(priestData)
        priestLine.name("Priest")
        priestLine.markers()
            .enabled(true)
            .type(MarkerType.CIRCLE)
            .size(3.0)

        radar.tooltip().format("Value: {%Value}")
        return ChartData(
            "Radar chart",
            "https://www.datanovia.com/en/wp-content/uploads/2020/12/radar-chart-in-r-customized-fmstb-radar-chart-1.png",
            radar
        )
    }

    fun getColumnChart(): ChartData{
        val chart = AnyChart.column()
        val data = mutableListOf<ValueDataEntry>()
        data.add(ValueDataEntry("A", 10))
        data.add(ValueDataEntry("B", 20))
        data.add(ValueDataEntry("C", 40))
        data.add(ValueDataEntry("D", 40))

        val column = chart.column(data.toList())

        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.LEFT_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0)
            .offsetY(5)

        chart.animation(true)
        chart.title("Column chart")
        chart.yScale().minimum(0)
        chart.tooltip().positionMode(TooltipPositionMode.POINT)
        chart.xAxis(0).title("Item")
        chart.yAxis(0).title("Value")

        return ChartData(
            "Column chart",
            "https://www.mongodb.com/docs/charts/images/charts/grouped-column-chart-reference-small.png",
            chart
        )
    }

    fun getVennChart(): ChartData{
        val venn = AnyChart.venn()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(NameValueDataEntry("A", "Data Science", 100))
        data.add(NameValueDataEntry("B", "Computer Science", 25))
        data.add(NameValueDataEntry("C", "Math and Statistics", 25))
        data.add(NameValueDataEntry("D", "Subject Matter Expertise", 25))
        data.add(NameValueDataEntry("A&B", "Computer Science", 50))
        data.add(NameValueDataEntry("A&C", "Math and Statistics", 50))
        data.add(NameValueDataEntry("A&D", "Subject Matter Expertise", 50))
        data.add(NameValueDataEntry("B&C", "Machine\\nLearning", 5))
        data.add(NameValueDataEntry("C&D", "Traditional\\nResearch", 5))
        data.add(NameValueDataEntry("D&B", "Traditional\\nSoftware", 5))
        data.add(NameValueDataEntry("B&C&D", "Unicorn", 5))

        venn.data(data)

        venn.stroke("2 #FFFFFF")

        venn.labels().format("{%Name}")

        venn.intersections().hovered().fill("black", 0.25)

        venn.intersections().labels().fontWeight("bold")
        venn.intersections().labels().format("{%Name}")

        venn.tooltip().titleFormat("{%Name}")
        return ChartData(
            "Venn chart",
            "https://static.anychart.com/images/gallery/v8/venn-diagram-santa-claus--god-and-spider-man-in-venn-chart.png",
            venn
        )
    }

    fun getLineChart(): ChartData{
        val cartesian = AnyChart.line()

        cartesian.animation(true)

        cartesian.padding(10.0, 20.0, 5.0, 20.0)

        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true) // TODO ystroke
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.")

        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData: MutableList<DataEntry> = ArrayList()
        seriesData.add(CustomDataEntry("1986", 3.6, 2.3, 2.8))
        seriesData.add(CustomDataEntry("1987", 7.1, 4.0, 4.1))
        seriesData.add(CustomDataEntry("1988", 8.5, 6.2, 5.1))
        seriesData.add(CustomDataEntry("1989", 9.2, 11.8, 6.5))
        seriesData.add(CustomDataEntry("1990", 10.1, 13.0, 12.5))
        seriesData.add(CustomDataEntry("1991", 11.6, 13.9, 18.0))
        seriesData.add(CustomDataEntry("1992", 16.4, 18.0, 21.0))
        seriesData.add(CustomDataEntry("1993", 18.0, 23.3, 20.3))
        seriesData.add(CustomDataEntry("1994", 13.2, 24.7, 19.2))
        seriesData.add(CustomDataEntry("1995", 12.0, 18.0, 14.4))
        seriesData.add(CustomDataEntry("1996", 3.2, 15.1, 9.2))
        seriesData.add(CustomDataEntry("1997", 4.1, 11.3, 5.9))
        seriesData.add(CustomDataEntry("1998", 6.3, 14.2, 5.2))
        seriesData.add(CustomDataEntry("1999", 9.4, 13.7, 4.7))
        seriesData.add(CustomDataEntry("2000", 11.5, 9.9, 4.2))
        seriesData.add(CustomDataEntry("2001", 13.5, 12.1, 1.2))
        seriesData.add(CustomDataEntry("2002", 14.8, 13.5, 5.4))
        seriesData.add(CustomDataEntry("2003", 16.6, 15.1, 6.3))
        seriesData.add(CustomDataEntry("2004", 18.1, 17.9, 8.9))
        seriesData.add(CustomDataEntry("2005", 17.0, 18.9, 10.1))
        seriesData.add(CustomDataEntry("2006", 16.6, 20.3, 11.5))
        seriesData.add(CustomDataEntry("2007", 14.1, 20.7, 12.2))
        seriesData.add(CustomDataEntry("2008", 15.7, 21.6, 10))
        seriesData.add(CustomDataEntry("2009", 12.0, 22.5, 8.9))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        val series1 = cartesian.line(series1Mapping)
        series1.name("Brandy")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series2 = cartesian.line(series2Mapping)
        series2.name("Whiskey")
        series2.hovered().markers().enabled(true)
        series2.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series2.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series3 = cartesian.line(series3Mapping)
        series3.name("Tequila")
        series3.hovered().markers().enabled(true)
        series3.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series3.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)
        return ChartData(
            "Line chart",
            "https://www.english-learning.net/wp-content/uploads/2021/05/line-graph.png",
            cartesian
        )
    }


    private class CustomDataEntry(x: String, value: Number, value2: Number, value3: Number): ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }

    fun getTagCloud(): ChartData{
        val tagCloud = AnyChart.tagCloud()

        tagCloud.title("World Population")

        val ordinalColor = OrdinalColor.instantiate()
        ordinalColor.colors(
            arrayOf(
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26"
            )
        )
        tagCloud.colorScale(ordinalColor)
        tagCloud.angles(arrayOf(-90.0, 0.0, 90.0))

        tagCloud.colorRange().enabled(true)
        tagCloud.colorRange().colorLineSize(15.0)

        val data: MutableList<DataEntry> = ArrayList()
        data.add(CategoryValueDataEntry("China", "asia", 1383220000))
        data.add(CategoryValueDataEntry("India", "asia", 1316000000))
        data.add(CategoryValueDataEntry("United States", "america", 324982000))
        data.add(CategoryValueDataEntry("Indonesia", "asia", 263510000))
        data.add(CategoryValueDataEntry("Brazil", "america", 207505000))
        data.add(CategoryValueDataEntry("Pakistan", "asia", 196459000))
        data.add(CategoryValueDataEntry("Nigeria", "africa", 191836000))
        data.add(CategoryValueDataEntry("Bangladesh", "asia", 162459000))
        data.add(CategoryValueDataEntry("Russia", "europe", 146804372))
        data.add(CategoryValueDataEntry("Japan", "asia", 126790000))
        data.add(CategoryValueDataEntry("Mexico", "america", 123518000))
        data.add(CategoryValueDataEntry("Ethiopia", "africa", 104345000))
        data.add(CategoryValueDataEntry("Philippines", "asia", 104037000))
        data.add(CategoryValueDataEntry("Egypt", "africa", 93013300))
        data.add(CategoryValueDataEntry("Vietnam", "asia", 92700000))
        data.add(CategoryValueDataEntry("Germany", "europe", 82800000))
        data.add(CategoryValueDataEntry("Democratic Republic of the Congo", "africa", 82243000))
        data.add(CategoryValueDataEntry("Iran", "asia", 80135400))
        data.add(CategoryValueDataEntry("Turkey", "asia", 79814871))
        data.add(CategoryValueDataEntry("Thailand", "asia", 68298000))
        data.add(CategoryValueDataEntry("France", "europe", 67013000))
        data.add(CategoryValueDataEntry("United Kingdom", "europe", 65110000))
        data.add(CategoryValueDataEntry("Italy", "europe", 60599936))
        data.add(CategoryValueDataEntry("Tanzania", "africa", 56878000))
        data.add(CategoryValueDataEntry("South Africa", "africa", 55908000))
        data.add(CategoryValueDataEntry("Myanmar", "asia", 54836000))
        data.add(CategoryValueDataEntry("South Korea", "asia", 51446201))
        data.add(CategoryValueDataEntry("Colombia", "america", 49224700))
        data.add(CategoryValueDataEntry("Kenya", "africa", 48467000))
        data.add(CategoryValueDataEntry("Spain", "europe", 46812000))
        data.add(CategoryValueDataEntry("Argentina", "america", 43850000))
        data.add(CategoryValueDataEntry("Ukraine", "europe", 42541633))
        data.add(CategoryValueDataEntry("Sudan", "africa", 42176000))
        data.add(CategoryValueDataEntry("Uganda", "africa", 41653000))
        data.add(CategoryValueDataEntry("Algeria", "africa", 41064000))
        data.add(CategoryValueDataEntry("Poland", "europe", 38424000))
        data.add(CategoryValueDataEntry("Iraq", "asia", 37883543))
        data.add(CategoryValueDataEntry("Canada", "america", 36541000))
        data.add(CategoryValueDataEntry("Morocco", "africa", 34317500))
        data.add(CategoryValueDataEntry("Saudi Arabia", "asia", 33710021))
        data.add(CategoryValueDataEntry("Uzbekistan", "asia", 32121000))
        data.add(CategoryValueDataEntry("Malaysia", "asia", 32063200))
        data.add(CategoryValueDataEntry("Peru", "america", 31826018))
        data.add(CategoryValueDataEntry("Venezuela", "america", 31431164))
        data.add(CategoryValueDataEntry("Nepal", "asia", 28825709))
        data.add(CategoryValueDataEntry("Angola", "africa", 28359634))
        data.add(CategoryValueDataEntry("Ghana", "africa", 28308301))
        data.add(CategoryValueDataEntry("Yemen", "asia", 28120000))
        data.add(CategoryValueDataEntry("Afghanistan", "asia", 27657145))
        data.add(CategoryValueDataEntry("Mozambique", "africa", 27128530))
        data.add(CategoryValueDataEntry("Australia", "australia", 24460900))
        data.add(CategoryValueDataEntry("North Korea", "asia", 24213510))
        data.add(CategoryValueDataEntry("Taiwan", "asia", 23545680))
        data.add(CategoryValueDataEntry("Cameroon", "africa", 23248044))
        data.add(CategoryValueDataEntry("Ivory Coast", "africa", 22671331))
        data.add(CategoryValueDataEntry("Madagascar", "africa", 22434363))
        data.add(CategoryValueDataEntry("Niger", "africa", 21564000))
        data.add(CategoryValueDataEntry("Sri Lanka", "asia", 21203000))
        data.add(CategoryValueDataEntry("Romania", "europe", 19760000))
        data.add(CategoryValueDataEntry("Burkina Faso", "africa", 19632147))
        data.add(CategoryValueDataEntry("Syria", "asia", 18907000))
        data.add(CategoryValueDataEntry("Mali", "africa", 18875000))
        data.add(CategoryValueDataEntry("Malawi", "africa", 18299000))
        data.add(CategoryValueDataEntry("Chile", "america", 18191900))
        data.add(CategoryValueDataEntry("Kazakhstan", "asia", 17975800))
        data.add(CategoryValueDataEntry("Netherlands", "europe", 17121900))
        data.add(CategoryValueDataEntry("Ecuador", "america", 16737700))
        data.add(CategoryValueDataEntry("Guatemala", "america", 16176133))
        data.add(CategoryValueDataEntry("Zambia", "africa", 15933883))
        data.add(CategoryValueDataEntry("Cambodia", "asia", 15626444))
        data.add(CategoryValueDataEntry("Senegal", "africa", 15256346))
        data.add(CategoryValueDataEntry("Chad", "africa", 14965000))
        data.add(CategoryValueDataEntry("Zimbabwe", "africa", 14542235))
        data.add(CategoryValueDataEntry("Guinea", "africa", 13291000))
        data.add(CategoryValueDataEntry("South Sudan", "africa", 12131000))
        data.add(CategoryValueDataEntry("Rwanda", "africa", 11553188))
        data.add(CategoryValueDataEntry("Belgium", "europe", 11356191))
        data.add(CategoryValueDataEntry("Tunisia", "africa", 11299400))
        data.add(CategoryValueDataEntry("Cuba", "america", 11239004))
        data.add(CategoryValueDataEntry("Bolivia", "america", 11145770))
        data.add(CategoryValueDataEntry("Somalia", "africa", 11079000))
        data.add(CategoryValueDataEntry("Haiti", "america", 11078033))
        data.add(CategoryValueDataEntry("Greece", "europe", 10783748))
        data.add(CategoryValueDataEntry("Benin", "africa", 10653654))
        data.add(CategoryValueDataEntry("Czech Republic", "europe", 10578820))
        data.add(CategoryValueDataEntry("Portugal", "europe", 10341330))
        data.add(CategoryValueDataEntry("Burundi", "africa", 10114505))
        data.add(CategoryValueDataEntry("Dominican Republic", "america", 10075045))
        data.add(CategoryValueDataEntry("Sweden", "europe", 10054100))
        data.add(CategoryValueDataEntry("United Arab Emirates", "asia", 10003223))
        data.add(CategoryValueDataEntry("Jordan", "asia", 9889270))
        data.add(CategoryValueDataEntry("Azerbaijan", "asia", 9823667))
        data.add(CategoryValueDataEntry("Hungary", "europe", 9799000))
        data.add(CategoryValueDataEntry("Belarus", "europe", 9498600))
        data.add(CategoryValueDataEntry("Honduras", "america", 8866351))
        data.add(CategoryValueDataEntry("Austria", "europe", 8773686))
        data.add(CategoryValueDataEntry("Tajikistan", "asia", 8742000))
        data.add(CategoryValueDataEntry("Israel", "asia", 8690220))
        data.add(CategoryValueDataEntry("Switzerland", "europe", 8417700))
        data.add(CategoryValueDataEntry("Papua New Guinea", "australia", 8151300))

        tagCloud.data(data)
        return ChartData(
            "Tag cloud",
            "https://www.researchgate.net/profile/Fabrice-Maurel/publication/316487816/figure/fig2/AS:488209458176000@1493409613451/Example-of-a-tag-cloud.png",
            tagCloud
        )
    }
}