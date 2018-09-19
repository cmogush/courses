//written by Christopher Mogush
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExporting {   
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(); //reset parser
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser(); //reset parser
        listExportersTwoProducts(parser, "fish", "nuts");
        parser = fr.getCSVParser(); //reset parser
        System.out.println(numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser(); //reset parser
        bigExporters(parser, "$999,999,999,999");
    }
        
    public String countryInfo(CSVParser parser, String country){
        //returns info about country
        for (CSVRecord record: parser){
            if(record.get("Country").equals(country)){
                return (record.get("Country") + " : " + record.get("Exports") + " : " + record.get("Value (dollars)"));
            }
        }
        //returns NOT FOUND if no info about country is found
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        //if country contains both export items, print name of country
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
                if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
                }
        }
    }
        
    public int numberOfExporters(CSVParser parser, String exportItem){
        //returns the number of countries that export exportItem
        int countNum = 0;
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                countNum += 1;
            }
        }
        return countNum;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        //prints the names of countries and their Value amount for al countries whose Value (dollars) string > amount
        int lengthAmount = amount.length();
        for (CSVRecord record : parser){    
            String value = record.get("Value (dollars)");
            int lengthString = value.length();
            if (lengthString > lengthAmount){
                System.out.println(record.get("Country") + ": " + value);
            }
        }
    }
}
