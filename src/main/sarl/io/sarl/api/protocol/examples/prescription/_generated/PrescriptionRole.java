package io.sarl.api.protocol.examples.prescription._generated;

import io.sarl.api.protocol.ProtocolRole;
import io.sarl.api.protocol.examples.prescription._generated.doctor.DoctorBehavior;
import io.sarl.api.protocol.examples.prescription._generated.doctor.DoctorProtocolCapacity;
import io.sarl.api.protocol.examples.prescription._generated.doctor.DoctorProtocolSkill;
import io.sarl.api.protocol.examples.prescription._generated.patient.PatientProtocolCapacity;
import io.sarl.api.protocol.examples.prescription._generated.patient.PatientProtocolSkill;
import io.sarl.api.protocol.examples.prescription._generated.pharmacist.PharmacistBehavior;
import io.sarl.api.protocol.examples.prescription._generated.pharmacist.PharmacistProtocolCapacity;
import io.sarl.api.protocol.examples.prescription._generated.pharmacist.PharmacistProtocolSkill;
import io.sarl.api.protocol.examples.prescription._generated.space.PrescriptionSpace;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Skill;

public enum PrescriptionRole implements ProtocolRole {
	PATIENT {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return PatientProtocolCapacity.class;
		}		
		
		@Override
		public Skill getProtocolSkill(EventSpace space) {
			return new PatientProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return null; // Return null because not behavior was _generated
		}

		@Override
		public void registerRole(EventSpace sp, EventListener listener) {
			// TODO How to remove cast operator with generic code in the space
			((PrescriptionSpace) sp).registerPatient(listener);
		}
		
		@Override
		public void unregisterRole(EventSpace sp, EventListener listener) {
			// TODO How to remove cast operator with generic code in the space
			((PrescriptionSpace) sp).unregisterPatient(listener);
		}
	},
	DOCTOR {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return DoctorProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(EventSpace space) {
			return new DoctorProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new DoctorBehavior(ag);
		}

		@Override
		public void registerRole(EventSpace sp, EventListener listener) {
			((PrescriptionSpace) sp).registerDoctor(listener);
		}
		
		@Override
		public void unregisterRole(EventSpace sp, EventListener listener) {
			((PrescriptionSpace) sp).unregisterDoctor(listener);
		}
	},
	PHARMACIST {
		@Override
		public Class<? extends Capacity> getProtocolCapacity() {
			return PharmacistProtocolCapacity.class;
		}	
		
		@Override
		public Skill getProtocolSkill(EventSpace space) {
			return new PharmacistProtocolSkill(space);
		}

		@Override
		public Behavior getProtocolBehavior(Agent ag) {
			return new PharmacistBehavior(ag);
		}

		@Override
		public void registerRole(EventSpace sp, EventListener listener) {
			((PrescriptionSpace) sp).registerPharmacist(listener);
		}
		
		@Override
		public void unregisterRole(EventSpace sp, EventListener listener) {
			((PrescriptionSpace) sp).unregisterPharmacist(listener);
		}
	};

}
