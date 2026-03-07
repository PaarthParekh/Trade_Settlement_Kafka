package Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name="envelope")
@XmlType(propOrder = {"authenticate","trading","order","security"})

public class TradeSettlement {
        private AttributeId authenticate;
        private AttributeId trading;
        private AttributeId order;
        private AttributeId security;

        public TradeSettlement(){}

        public TradeSettlement(AttributeId authenticate,AttributeId trading,AttributeId order, AttributeId security)
        {
            this.authenticate=authenticate;
            this.trading=trading;
            this.order=order;
            this.security=security;
        }


        @XmlElement(name ="authenticate")
        public AttributeId getAuthenticate () {return authenticate; }
        public void setAuthenticate(AttributeId authenticate) {this.authenticate= authenticate; }

        @XmlElement (name ="trading")
        public AttributeId getTrading () {return trading; }
        public void setTrading(AttributeId trading) {this.trading= trading; }

        @XmlElement (name ="order")
        public AttributeId getOrder () {return order; }
        public void setOrder(AttributeId order) {this.order= order; }

        @XmlElement (name ="security")
        public AttributeId getSecurity () {return security; }
        public void setSecurity(AttributeId security) {this.security= security; }

    }
