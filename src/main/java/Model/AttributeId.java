package Model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class AttributeId {

        private Integer id;
        private Integer crdId;
        private Integer scenarioId;
        private String orderRun;

        AttributeId(){}

        public AttributeId(String orderRun)
        {
                this.id=null;
                this.crdId=null;
                this.orderRun=orderRun;
        }

        public AttributeId(Integer id,String orderRun)
        {
                this.id= id;
                this.crdId= null;
                this.orderRun=orderRun;
        }

        public AttributeId(Integer id,Integer crdId,String orderRun)
        {
            this.id=id;
            this.crdId=crdId;
            this.orderRun=orderRun;
        }

        @XmlAttribute (name="id", required = false)
        public Integer getId() {return id;}
        public void setId (Integer id) {this.id=id;}

        @XmlAttribute (name="crdId", required = false)
        public Integer getCrdId (){return crdId;}
        public void setCrdId( Integer crdId) {this.crdId=crdId;}

        @XmlValue
        public String getOrderRun (){return orderRun;}
        public void setOrderRun( String orderRun) {this.orderRun=orderRun;}

    }
