package org.example;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXMLFileInJava {


    /**
     *
     *
     * <cac:InvoiceLine>
     * <cbc:ID>1</cbc:ID>
     * <cbc:InvoicedQuantity unitCode="PCE">33.000000</cbc:InvoicedQuantity>
     * <cbc:LineExtensionAmount currencyID="SAR">99.00</cbc:LineExtensionAmount>
     * <cac:TaxTotal>
     * <cbc:TaxAmount currencyID="SAR">14.85</cbc:TaxAmount>
     * <cbc:RoundingAmount currencyID="SAR">113.85</cbc:RoundingAmount>
     * </cac:TaxTotal>
     * <cac:Item>
     * <cbc:Name>كتاب</cbc:Name>
     * <cac:ClassifiedTaxCategory>
     * <cbc:ID>S</cbc:ID>
     * <cbc:Percent>15.00</cbc:Percent>
     * <cac:TaxScheme>
     * <cbc:ID>VAT</cbc:ID>
     * </cac:TaxScheme>
     * </cac:ClassifiedTaxCategory>
     * </cac:Item>
     * <cac:Price>
     * <cbc:PriceAmount currencyID="SAR">3.00</cbc:PriceAmount>
     * <cac:AllowanceCharge>
     * <cbc:ChargeIndicator>false</cbc:ChargeIndicator>
     * <cbc:AllowanceChargeReason>discount</cbc:AllowanceChargeReason>
     * <cbc:Amount currencyID="SAR">0.00</cbc:Amount>
     * </cac:AllowanceCharge>
     * </cac:Price>
     * </cac:InvoiceLine>
     *
     * */

    private static void addInvoiceLineItem(Document doc){
        NodeList invoices = doc.getElementsByTagName("Invoice");
       Element invoice = null;

        // loop for each user
        for (int i = 0; i < invoices.getLength(); i++) {
            invoice = (Element) invoices.item(i);
            Element invoiceLineItem =  doc.createElement( "cac:InvoiceLine" );

            invoice.appendChild(invoiceLineItem);
        }

    }


    public static void main(String[] args) {
        String filePath = ClassLoader.getSystemResource(   "Simplified_Invoice.xml").getPath();
        File xmlFile = new File( filePath );
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse( xmlFile );

            doc.getDocumentElement().normalize();
            addInvoiceLineItem( doc );

            // update Element value
           // updateElementValue( doc );

            // delete element
           // deleteElement( doc );

            // add new element
           // addElement( doc );

            // write the updated document to file or console
            writeXMLFile( doc );
        } catch( Exception e1 ) {
            e1.printStackTrace();
        }
    }

    private static void writeXMLFile(Document doc)
        throws TransformerFactoryConfigurationError, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("users_updated.xml"));
        transformer.setOutputProperty( OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
    }

    /**
     * Add a new element salary to user element.
     * @param doc
     */
    private static void addElement(Document doc) {
        NodeList users = doc.getElementsByTagName("User");
        Element emp = null;

        // loop for each user
        for (int i = 0; i < users.getLength(); i++) {
            emp = (Element) users.item(i);
            Element salaryElement = doc.createElement("salary");
            salaryElement.appendChild(doc.createTextNode("10000"));
            emp.appendChild(salaryElement);
        }
    }

    /**
     * Delete gender element from User element
     * @param doc
     */
    private static void deleteElement(Document doc) {
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
        // loop for each user
        for (int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            Node genderNode = user.getElementsByTagName("gender").item(0);
            user.removeChild(genderNode);
        }

    }

    /**
     * Update firstName element value to Upper case.
     * @param doc
     */
    private static void updateElementValue(Document doc) {
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
        // loop for each user
        for (int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            Node name = user.getElementsByTagName("firstName").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }
}