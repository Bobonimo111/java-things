package org.example;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class PdfTemplatRender {
    private final Document document;

    private PdfPTable header = null;
    private PdfPTable tableHeader = null;
    private PdfPTable tableBody = null;


    /*Nesta area deve ficar imagem de cabeçalho além de titulo principal
    *The icon has need 500x500px*/
    public void setHeader(String icon, String title,Font fontStyle) throws DocumentException, IOException {
        PdfPTable headerTable = new PdfPTable(3);
        //Define a quantidade de ocupação da tela deacordo com o documento
        headerTable.setWidthPercentage(90);
        //Define a largura da colunas
        headerTable.setWidths(new float[]{1f,4f,1f});

        Image imageIcon = Image.getInstanceFromClasspath(icon);
        imageIcon.scalePercent(15);
        //Definindo configurações da celula
        PdfPCell imageCell = new PdfPCell(imageIcon);
        imageCell.setBorder(Rectangle.NO_BORDER);
        imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);


        //Definindo o Titulo principal
        PdfPCell titleCell = new PdfPCell(new Paragraph(title,fontStyle));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);


        //Adicionando celula vazia
        PdfPCell noneCell = new PdfPCell(new Paragraph(""));
        noneCell.setBorder(Rectangle.NO_BORDER);

        //Posicionando as celulas
        headerTable.addCell(imageCell);
        headerTable.addCell(titleCell);
        headerTable.addCell(noneCell);

        //Definindo o header para variavel global
        this.header = headerTable;
    }
    public void setHeader(String title, Font fontStyle) throws DocumentException{
        PdfPTable headerTable = new PdfPTable(3);
        //Define a quantidade de ocupação da tela deacordo com o documento
        headerTable.setWidthPercentage(90);
        //Define a largura da colunas
        headerTable.setWidths(new float[]{1f,4f,1f});

        //Definindo o Titulo principal
        PdfPCell titleCell = new PdfPCell(new Paragraph(title,fontStyle));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);


        //Adicionando celula vazia
        PdfPCell noneCell = new PdfPCell(new Paragraph(""));
        noneCell.setBorder(Rectangle.NO_BORDER);

        //Posicionando as celulas
        headerTable.addCell(noneCell);
        headerTable.addCell(titleCell);
        headerTable.addCell(noneCell);

        //Definindo o header para variavel global
        this.header = headerTable;
    }
    public PdfPTable getHeader() {
        return header;
    }

    public void setTableHeader(String[] index,int columnsNum){
        if (index.length != columnsNum) {
            throw new IllegalArgumentException("O tamanho do array 'index' deve ser igual a 'columnsNum'.");
        }

        PdfPTable tableHeader = new PdfPTable(columnsNum);
        //Define a quantidade de ocupação da tela deacordo com o documento
        tableHeader.setWidthPercentage(90);
        //Define a largura da colunas
        float[] collumns = new float[columnsNum];
        Arrays.fill(collumns,1f);
        tableHeader.setWidths(collumns);
        for(String i : index){
            tableHeader.addCell(new Paragraph(i,new Font(Font.HELVETICA,16,Font.BOLD)));
        }
        this.tableHeader = tableHeader;
    }
    public void setTableHeader(String[] index,int columnsNum,Font textStyle){
        if (index.length != columnsNum) {
            throw new IllegalArgumentException("O tamanho do array 'index' deve ser igual a 'columnsNum'.");
        }

        PdfPTable tableHeader = new PdfPTable(columnsNum);
        //Define a quantidade de ocupação da tela deacordo com o documento
        tableHeader.setWidthPercentage(90);
        //Define a largura da colunas
        float[] collumns = new float[columnsNum];
        Arrays.fill(collumns,1f);
        tableHeader.setWidths(collumns);
        for(String i : index){
            tableHeader.addCell(new Paragraph(i,textStyle));
        }
        this.tableHeader = tableHeader;
    }
    public PdfPTable getTableHeader(){return tableHeader;}

    /*Cada linha é um dado de um usuario*/
    public void setTableBody(String[][] dados,int columnsNum){
        PdfPTable tableBody = new PdfPTable(columnsNum);
        //Define a quantidade de ocupação da tela deacordo com o documento
        tableBody.setWidthPercentage(90);
        //Define a largura da colunas
        float[] collumns = new float[columnsNum];
        Arrays.fill(collumns,1f);
        tableBody.setWidths(collumns);
        for(String[] conjuntoDeDados : dados){
            for(String dadoUnico: conjuntoDeDados){
                tableBody.addCell(new Paragraph(dadoUnico,new Font(Font.HELVETICA,12,Font.NORMAL)));
            }
        }
        this.tableBody = tableBody;
    }
    /*Cada linha é um dado de um usuario*/
    public void setTableBody(String[][] dados,int columnsNum, Font textStyle){
        PdfPTable tableBody = new PdfPTable(columnsNum);
        //Define a quantidade de ocupação da tela deacordo com o documento
        tableBody.setWidthPercentage(90);
        //Define a largura da colunas
        float[] collumns = new float[columnsNum];
        Arrays.fill(collumns,1f);
        tableBody.setWidths(collumns);
        for(String[] conjuntoDeDados : dados){
            for(String dadoUnico: conjuntoDeDados){
                tableBody.addCell(new Paragraph(dadoUnico,textStyle));
            }
        }
        this.tableBody = tableBody;
    }

    public PdfPTable getTableBody(){return tableBody;}

    public void addDocument(Element e){
        this.document.add(e);
    }
    public void write(){
        this.document.close();
    }

    private void checkAndCreatePdfPath(){
        File pasta = new File("pdfs");
        if (!pasta.exists()) {
            if (pasta.mkdirs()) {
                System.out.println("Pasta criada com sucesso!");
            } else {
                System.out.println("Falha ao criar a pasta.");
            }
        } else {
            System.out.println("A pasta já existe.");
        }
    }
    PdfTemplatRender(Rectangle pageSize,String outputName) throws DocumentException, FileNotFoundException {
        this.checkAndCreatePdfPath();
        long timeStamp = System.currentTimeMillis();
        this.document = new Document(pageSize);
        // Cria o documento PDF
        // Cria o writer que escreve o conteúdo no arquivo
        PdfWriter.getInstance(this.document, new FileOutputStream("pdfs"+File.separator+timeStamp + "_" + outputName));
        // Abre o documento para edição
        this.document.open();
    }
}
