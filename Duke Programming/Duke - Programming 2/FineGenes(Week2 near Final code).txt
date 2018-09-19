//Created by Christopher Mogush
import edu.duke.*;

public class FindGenes {
    public void testRealDna(){
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString();
    dna = dna.toUpperCase();
    StorageResource genes = getAllGenes(dna);
    //printAllGenes(genes);
    processGenes(genes);
    System.out.println("Number of times CTG codone appears: " + countCTG(dna));
    }
    
    public void test(){
    String dna1 = "aATgTCACtagCaTAaAtgCCCTGAactG";
    //String dna2 = "ccccggggggggg";
    StorageResource genes = getAllGenes(dna1);
    printAllGenes(genes);
    //System.out.println("C:G ratio is: " + cgRatio(dna1));
    //System.out.println("CTG codon count is: " + countCTG(dna1));
    }
    
    public void testProcessGenes(){
    String dna1 = "ATGAAAAGCTGAATGCTGAAATAA"; //genes longer than 9 char
    //String dna2 = "atgcacTAGATGAAbTAaATGCaBTGA"; // no genes longer than 9 char
    //String dna3 = "atgcacTAGATGAAbTAaATGCaBTGAcccACg"; // some genes C-G ratio higher than .35
    //String dna4 = "atgcacTAGATGAAbTAaATGCaBTGAggaggaggG"; // some genes C-G ratio lower than .35
    //String dna5 = "atgcacTAGATGAAbTAaATGCaBTGACATgatGCCACTatGaagcacacTGAATGCCATCTGggaatTGATATAATATAAggTTCCC"; // whatever you want
    System.out.println("DNA strand is: " + dna1);
    StorageResource genes = getAllGenes(dna1);
    System.out.println("Genes found are: ");
    printAllGenes(genes);
    processGenes(genes);
    //printAllGenes(genes);
    //System.out.println("C:G ratio is: " + cgRatio(dna1));
    //System.out.println("CTG codon count is: " + countCTG(dna1));
    }
    
    public void printAllGenes(StorageResource genes){
        for (String g : genes.data()){
        System.out.println(g);
        } 
    }
    
    public void processGenes(StorageResource genes){
        int countChar = 0;
        int countGenes = 0;
        System.out.println("Genes strands with length greater than 60:");
        for(String sr : genes.data()){
            if(sr.length() > 60){
            countChar += 1;
            }
            countGenes += 1;
            System.out.println(countGenes);
        }
        System.out.println("Total # of Genes: " + countGenes);
        System.out.println("Total # with length > 60: " + countChar);
        
        int countCG = 0;
        //System.out.println("Genes with C-G ratio higher than 0.35");
        for(String sr : genes.data()){
            if(cgRatio(sr) > 0.35){
            //System.out.println(sr + ", with a C-G ratio of: " + cgRatio(sr));
            countCG += 1;
            }
        }
        System.out.println("Total # with CG ratio > 0.35: " + countCG);
        
        String prevGene = "";
        String longestGene = "";
        for(String sr : genes.data()){
            if(sr.length() > longestGene.length()){
                longestGene = sr;
            }
        } 
        System.out.println("Longest gene is: " + longestGene);
        System.out.println("Length of longest gene is: " + longestGene.length());

    }
        
    public StorageResource getAllGenes(String dna){
    StorageResource geneList = new StorageResource();
    String gene = "";
    int geneCount = 1;
    int startIndex = 0;
    //System.out.println("DNA strand is: " + dna);
    while (gene != "n/a"){
        gene = findSimpleGene(dna, startIndex);
        if(dna.indexOf(gene) == -1){
        break;
        }
        geneList.add(gene);
        startIndex = dna.indexOf(gene) + gene.length();
        //System.out.println("Gene # " + geneCount + " is: " + gene);
        geneCount = geneCount + 1;
    }
    return geneList;
    }
    
    public String findSimpleGene(String dna, int dnaIndex){
        String upDNA = dna.toUpperCase();//convert to caps
        String gene = "";
        int startIndex = upDNA.indexOf("ATG", dnaIndex); // gets start index #      
        int stopIndex = 0;
        String check = "not divisible by 3";
        int currIndex = startIndex + 3; //sets currIndex after ATG
        while (check == "not divisible by 3"){
        if (startIndex == -1 || currIndex == -1 || stopIndex == -1 || stopIndex + 3 > dna.length()){
            return "n/a";
            }
        String stopCodon = determineStopCodon(upDNA, currIndex);
        stopIndex = upDNA.indexOf(stopCodon, currIndex); //looks for stop codon after start index
        if (startIndex == -1 || currIndex == -1 || stopIndex == -1 || stopIndex + 3 > dna.length()){
            return "n/a";
            }
        gene = dna.substring(startIndex, stopIndex + 3);
        if(gene.length() % 3 == 0){
                check = "ok";
            }
        else{
                currIndex = stopIndex + 1;
            }
        }
     
    return gene;
    }
    
    public String determineStopCodon(String dna, int startIndex){
    int TAA = dna.indexOf("TAA", startIndex + 3);
    int TAG = dna.indexOf("TAG", startIndex + 3);
    int TGA = dna.indexOf("TGA", startIndex + 3);
    int stopCodon = (compareTwoVariables(compareTwoVariables(TAA, TAG),TGA));
    if(TAA == stopCodon){
        return "TAA";
    }
    if(TAG == stopCodon){
        return "TAG";
    }
    if(TGA == stopCodon){
        return "TGA";
    }
    return "error";
    } 
    
    public int compareTwoVariables(int var1, int var2){
        int min = 0;
        if(var1 != -1 && var2 != -1){
            min = Math.min(var1, var2);
        }
        else {
            min = -1;
        }
        if(var1 != -1 && var2 == -1){
            min = var1;
        }
        if(var1 == -1 && var2 != -1){
            min = var2;
        }
    return min;
    }
    
    public float cgRatio(String dna){
        dna = dna.toUpperCase();
        float c = 0;
        int indexC = dna.indexOf("C");
        while (indexC != -1){
            c += 1;
            indexC = dna.indexOf("C", indexC + 1);
        }
        //System.out.println(c);
        float g = 0;
        int indexG = dna.indexOf("G");
        while (indexG != -1){
            g += 1;
            indexG = dna.indexOf("G", indexG + 1);
        }
        //System.out.println(g);
        float ratio = (c+g)/dna.length();
        return ratio;
        
    }
    
    public int countCTG(String dna){
    dna = dna.toUpperCase();
    int ctg = 0;
    int indexCTG = dna.indexOf("CTG");
    while (indexCTG > 0){
        ctg = ctg + 1;
        indexCTG = dna.indexOf("CTG", indexCTG + 3);
    }
    return ctg;
    }
}