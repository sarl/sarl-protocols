package io.sarl.api.protocol.examples.prescription.adapter;

import io.sarl.api.protocol.ProtocolRole;
import io.sarl.api.protocol.ProtocolSpace;
import io.sarl.api.protocol.examples.prescription.adapter.doctor.DoctorBehavior;
import io.sarl.api.protocol.examples.prescription.adapter.doctor.DoctorProtocolCapacity;
import io.sarl.api.protocol.examples.prescription.adapter.doctor.DoctorProtocolSkill;
import io.sarl.api.protocol.examples.prescription.adapter.patient.PatientBehavior;
import io.sarl.api.protocol.examples.prescription.adapter.patient.PatientProtocolCapacity;
import io.sarl.api.protocol.examples.prescription.adapter.patient.PatientProtocolSkill;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistBehavior;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistProtocolCapacity;
import io.sarl.api.protocol.examples.prescription.adapter.pharmacist.PharmacistProtocolSkill;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.Skill;

public enum PrescriptionRole implements ProtocolRole {
	PATIENT {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return PatientProtocolCapacity.class;
		}		
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new PatientProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new PatientBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}
	},
	DOCTOR {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return DoctorProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new DoctorProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new DoctorBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}
	},
	PHARMACIST {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return PharmacistProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(ProtocolSpace space) {
			return new PharmacistProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new PharmacistBehavior(ag);
		}

		@Override
		public int getMaxCardinality() {
			return 1;
		}

	};

}
