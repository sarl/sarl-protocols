package io.sarl.api.protocol.examples.netbill.adapter;

import io.sarl.api.protocol.ProtocolCapacity;
import io.sarl.api.protocol.ProtocolRole;
import io.sarl.api.protocol.ProtocolSpace;
import io.sarl.api.protocol.examples.netbill.adapter.customer.CustomerProtocolCapacity;
import io.sarl.api.protocol.examples.netbill.adapter.customer.CustomerProtocolSkill;
import io.sarl.api.protocol.examples.netbill.adapter.customer.CustomerReactiveBehavior;
import io.sarl.api.protocol.examples.netbill.adapter.merchant.MerchantProtocolCapacity;
import io.sarl.api.protocol.examples.netbill.adapter.merchant.MerchantProtocolSkill;
import io.sarl.api.protocol.examples.netbill.adapter.merchant.MerchantReactiveBehavior;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistBehavior;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistProtocolCapacity;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistProtocolSkill;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Skill;

public enum NetbillRole implements ProtocolRole {
	CUSTOMER {
		@Override
		public Class<? extends ProtocolCapacity> getProtocolCapacity() {
			return CustomerProtocolCapacity.class;
		}		
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new CustomerProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new CustomerReactiveBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}
	},
	MERCHANT {
		@Override
		public Class<? extends ProtocolCapacity> getProtocolCapacity() {
			return MerchantProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new MerchantProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new MerchantReactiveBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}
	};

}
