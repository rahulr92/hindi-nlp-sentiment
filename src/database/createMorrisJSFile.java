package database;

public class createMorrisJSFile {

 public String createDonutChart(int senti[]){
	 String jsCode = null;
	 jsCode = "    Morris.Donut({"
        + "element: 'morris-donut-chart',"
        +"data: [{"
         +"   label: \"Positive\", value: "
          +senti[2]+
        "}, {"
        +"    label: \"Negative\", value: "
         +senti[1]+
       " }, {"+
           " label: \"Neutral\",  value: "
        +senti[0]+
        "}],"+
       "resize: true"+
   " });";
	return jsCode;
	 
 }
};
