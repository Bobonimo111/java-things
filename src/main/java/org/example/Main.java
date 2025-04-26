package org.example;


import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[][] animais = {
                {"Leão", "Savana", "Amarelo", "4"},
                {"Águia", "Montanhas", "Marrom", "2"},
                {"Jacaré", "Pantanal", "Verde", "4"},
                {"Urso Polar", "Ártico", "Branco", "4"},
                {"Canguru", "Deserto", "Marrom", "2"},
                {"Pinguim", "Antártida", "Preto e Branco", "2"},
                {"Elefante", "Savana", "Cinza", "4"},
                {"Cobra", "Floresta Tropical", "Verde", "0"},
                {"Cervo", "Floresta Temperada", "Marrom", "4"}
        };

        try {
            PdfTemplatRender pdf = new PdfTemplatRender(PageSize.A4,"teste.pdf");


            pdf.setHeader("icon.jpg","titulo fodastico", new Font(Font.HELVETICA,16,Font.NORMAL));
            pdf.setTableHeader(new String[]{"Especie","Bioma","Cor principal","quantidade de patas"},4);
            pdf.setTableBody(animais,4);

            pdf.addDocument(pdf.getHeader());
            pdf.addDocument(pdf.getTableHeader());
            pdf.addDocument(pdf.getTableBody());

            pdf.write();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}