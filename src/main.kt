
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

fun main() {

    val file = File ("c://xml/nsiPurchaseMethod_all_20190310_013244_001.xml")
    var factory:DocumentBuilderFactory = DocumentBuilderFactory.newDefaultInstance()
    var builder:DocumentBuilder = factory.newDocumentBuilder()
    var document: Document = builder.parse(file)

    var employersElement: Element =  document.getElementsByTagName("ns2:body").item(0) as Element
   // var text:String =  employersElement.getElementsByTagName("guid").item(0).textContent

    var nodaListItem : NodeList = document.getElementsByTagName("ns2:item")
    var listItem : List<Unit>
    var i: Int = 0
    var listLength : Int = nodaListItem.length

    while (i<nodaListItem.length)
    {
        var unit1 = govno()
        if (nodaListItem.item(i).nodeType == Node.ELEMENT_NODE){
            var  elementBody : Element = nodaListItem.item(i) as Element
            //if (i==0) {
            unit1.guidFullDocument = elementBody.getElementsByTagName("guid").item(0).textContent

            //} else {
                var nodaListChild: NodeList = elementBody.getElementsByTagName("ns2:nsiPurchaseMethodData").item(0).childNodes
               // println(elementBody.getElementsByTagName("ns2:nsiPurchaseMethodData").item(0).textContent)
                var j: Int = 0
                while (j < nodaListChild.length) {
                    if (nodaListChild.item(j).nodeType == Node.ELEMENT_NODE) {
                        var childElemen: Element = nodaListChild.item(j) as Element
                        when (childElemen.tagName.toString()){
                            "ns2:createDateTime" -> unit1.createDateTime = LocalDateTime.parse(childElemen.textContent, DateTimeFormatter.ISO_ZONED_DATE_TIME)
                            "ns2:changeDateTime" -> unit1.changeDateTime = LocalDateTime.parse(childElemen.textContent, DateTimeFormatter.ISO_ZONED_DATE_TIME)
                            "ns2:startDateActive" -> unit1.startDateActive = LocalDate.parse(childElemen.textContent, DateTimeFormatter.ISO_DATE)
                            "ns2:businessStatus" -> unit1.businessStatus = childElemen.textContent.toInt()
                            "ns2:code" ->  unit1.code = childElemen.textContent.toLong()
                            "ns2:name" -> unit1.name = childElemen.textContent.toString()
                            "ns2:parentCode" -> unit1.parentCode = childElemen.textContent.toLong()
                            "ns2:orderNumber" -> unit1.orderNumber = childElemen.textContent.toInt()
                            "ns2:isElectronic" -> unit1.isElectronic = childElemen.textContent.toBoolean()
                            "ns2:creator" -> {
                                var nodaListCustomer: NodeList = childElemen.childNodes//getElementsByTagName("ns2:creator").item(0).childNodes
                                var k: Int = 0
                                while (k<nodaListCustomer.length){
                                    if (nodaListChild.item(k).nodeType == Node.ELEMENT_NODE){
                                        var customerElement: Element = nodaListCustomer.item(k) as Element
                                        when (customerElement.tagName.toString()) {
                                            "inn" -> unit1.creator.inn = customerElement.textContent
                                            "kpp" -> unit1.creator.kpp = customerElement.textContent
                                            "ogrn" -> unit1.creator.ogrn = customerElement.textContent
                                        }
                                    }
                                    k+=1
                                }
                            }
                            "ns2:extended" -> unit1.extended = childElemen.textContent.toBoolean()
                            "ns2:competitive" -> unit1.competitive = childElemen.textContent.toBoolean()
                        }

                    }
                    j += 1
                }
            //}
            println(unit1.toString())
            i += 1
        }
    }
}


