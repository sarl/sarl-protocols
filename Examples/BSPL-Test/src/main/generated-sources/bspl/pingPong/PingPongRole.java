/* This file was automatically generated. Do not change its content. */

package pingPong;

import io.sarl.extensions.bspl.api.protocol.impl.ProtocolCapacity;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolRole;
import io.sarl.extensions.bspl.api.protocol.impl.ProtocolSpace;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Skill;

public enum PingPongRole implements ProtocolRole {
  ping {
    public Class<? extends ProtocolCapacity> getProtocolCapacity() {
      return pingProtocolCapacity.class;
    }
    public Skill getProtocolSkill(ProtocolSpace space) {
      return new pingProtocolSkill(space);
    }
    public Behavior getProtocolBehavior(Agent ag) {
      return new pingProtocolReactiveBehavior(ag);
    }
  },
  pong {
    public Class<? extends ProtocolCapacity> getProtocolCapacity() {
      return pongProtocolCapacity.class;
    }
    public Skill getProtocolSkill(ProtocolSpace space) {
      return new pongProtocolSkill(space);
    }
    public Behavior getProtocolBehavior(Agent ag) {
      return new pongProtocolReactiveBehavior(ag);
    }
  };
}