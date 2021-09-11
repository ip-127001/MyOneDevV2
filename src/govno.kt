
import java.time.LocalDate
import java.time.LocalDateTime

class govno {

    var guidFullDocument: String? = null
    var createDateTime: LocalDateTime? = null
    var changeDateTime: LocalDateTime? = null
    var startDateActive: LocalDate? = null

    var businessStatus : Int = -1
    var code : Long=-1
    var name : String =""
    var parentCode : Long =-1
    var orderNumber : Int = -1
    var isElectronic : Boolean? = null
    var creator = creator()
    var extended : Boolean? = null
    var competitive : Boolean? = null
    var numberTest : Int = -1

     @Override
     override fun toString ()
     : String {
         return "Object " +
                 "\t $guidFullDocument \t $createDateTime \t $changeDateTime \t  $startDateActive \t $code \t $name \t $parentCode \t $orderNumber \t $isElectronic  " +
                 "\t ${creator.inn} \t ${creator.kpp} \t ${creator.ogrn} \t $extended \t $competitive"
     }
}

class creator{
    var fullName : String? = null
    var shortName : String? = null
    var inn:  String? = null
    var kpp:  String? = null
    var ogrn: String? = null
    var legalAddress : String? = null
    var postalAddress : String? = null
    }

