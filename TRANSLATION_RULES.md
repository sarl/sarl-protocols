# 1. Introduction

Let a BSPL-like notation:

$$
B :=
\begin{align}
v_1 &: String \\
v_2 &: String \\
v_3 &: String \\
R_1 & \rightarrow R_2 : M_1 [\text{in } v_1 \text{ out } v_2] \\
R_2 & \rightarrow R_3 : M_2 [\text{in } v_1 \text{ in } v_2 \text{ out } v_3]
\end{align}
$$

Let:
$$R = \{R_1, R_2, R_3\}$$

$$R_e \subseteq R = \{R_1, R_2\}$$

$$R_r \subseteq R = \{R_2, R_3\}$$

$$M = \{M_1, M_2\}$$

$$V = \{v_1, v_2, v_3\}$$

$$V_{in} \subseteq V = \{v_1, v_2\}$$

$$V_{out} \subseteq V = \{v_2, v_3\}$$


Let a BSPL rule defined as:

$$\langle i, o, m, A, E\rangle$$

with: $i \in R_e$, $o \in R_r$, $m \in M$, $A \subseteq V_{in}$ and $E \subseteq V_{out}$.

# 2. Generated SARL constructs that are independent of the agent behaviors

## 2.1. Definition of the messages / events

Creation of a SARL event per BSPL message to transport the information between two agents:

> Loop for each $m \in M$:  
$\quad U = \{v | \forall v \in A \cup E, u = \langle \square,\square,m,A,E\rangle, \forall u \in B\}$  
$\quad \oplus($"event" $m$ "{"$)$  
$\quad$Loop for each $v \in U$:  
$\qquad\oplus($"var" $v$ ":" typeof($v$)$)$  
$\quad\oplus($"new("$)$  
$\quad$Loop for each $v \in U$:  
$\qquad\oplus(v$ ":" typeof($v$)$)$  
$\quad\oplus($") {"$)$  
$\quad$Loop for each $v \in U$:  
$\qquad\oplus($"this." $v$ "=" v$)$  
$\quad\oplus($"}" "}"$)$  
>
>$\quad\oplus($"event" $m$ "Enabled" $)$

where $\square$ stands for a free variable, and $\oplus(x)$ generates SARL code $x$.

Creation of a SARL "bound" event for each variable in BSPL that indicates to the agent that the knowledge $v$ was bounded to its value:

> Loop for each $v \in V$:  
$\quad \oplus($"event" $v$ "Bound"$)$


## 2.2. Definition of the protocol roles

Each participant to a protocol plays a role.

Each role definition is an item of a Java enumeration in which the key functions are defined.
The capacity/skill is for the emitters of a message; and, the behavior is for the received of a message:

* `getProtocolCapacity`: to provide the name of the capacity associated to the protocol for the role;
* `getProtocolSkill(space)`: to provide the instance of the skill that implements the previous capacity; the provided `space` is the space that is used for exchanging protocol events;
* `getProtocolBehavior(agent)`: to provide the instance of the behavior that implements the generic receiver behavior; the provided `agent` is the role player;
* `getMaxCardinality`: to return the maximum number of players for this role;
* `getMinCardinality`: to return the minimum number of players for this role. It is $1$ is this function is not defined;

Definition of all the roles in the protocol:

> $\oplus($"enum" $B$ "Role" "implements" "ProtocolRole" "{"$)$  
Loop for each $r \in R$:  
$\quad\oplus(r$ "{"$)$  
$\quad\oplus($"def" "getProtocolCapacity" ":" "Class<? extends Capacity>" "{" $r$ "ProtocolCapacity" "}"$)$  
>
> $\quad\oplus($"def" "getProtocolSkill" "(" "space" ":" "ProtocolSpace" ")" ":" "Skill"$)$  
$\quad\oplus($"{" "new" $r$ "ProtocolSkill(space)" "}"$)$  
>
> $\quad\oplus($"def" "getProtocolBehavior" "(" "^agent" ":" "Agent" ")" ":" "Behavior"$)$  
$\quad\oplus($"{" "new" $r$ "Behavior(^agent)" "}"$)$  
>
> $\quad\oplus($"def" "getMaxCardinality" ":" "int" "{" "1" "}"$)$  
$\quad\oplus($"}"$)$  
$\oplus($"}"$)$


## 2.3. Definition of the protocol space (specification)

The protocol space is generic and independent of any specific protocol.
A protocol defines a space specification (a kind of factory for the space) that specifies the accepted roles in the protocol:

> $\oplus($"class" $B$ "SpaceSpecification" "extends" "AbstractProtocolSpaceSpecification" "{"$)$  
$\oplus($"def" "getRoles" ":" "ProtocolRole[]" "{" $B$ "Role.values" "}" "}"$)$  


## 2.4. Definition of the protocol capacity

The players of the roles are equiped with a capacity and a skill for storing the bound in and out variables.
All the variables that are bound in a message are accessible by the roles attached to the message.

> Loop for each $r \in R$:  
$\quad\oplus($"capacity" $r$ "ProtocolCapacity" "{"$)$  
$\quad l = \{v | \forall v \in A \cup E, u = \langle X,Y,\square,A, E\rangle, r \in \{X, Y\}, \forall u \in B\}$  
$\quad$Loop for each $v \in l$:  
$\qquad\oplus($"def" "set" $v$ "(" "v" ":" typeof($v$) ")"$)$  
$\qquad\oplus($"def" "get" $v$ ":" typeof($v$)$)$  
$\quad\oplus($"}"$)$

## 2.5. Definition of the protocol skill

> Loop for each $r \in R$:  
>$\quad\oplus($"skill" $r$ "ProtocolSkill" "implements" "ProtocolCapacity" "{"$)$  
>
>$\quad\oplus($"uses" "Logging" "Behaviors"$)$  
>
>$\quad\oplus($"val" "protocolSpace" ":" "ProtocolSpace"$)$  
>
>$\quad\oplus($"new" "(" "protocolSpace" ":" "ProtocolSpace" ")" "{" "this.protocolSpace" "=" "protocolSpace" "}"$)$  
>
>$\quad l = \{v | \forall v \in A \cup E, u = \langle X,Y,\square,A, E\rangle, r \in \{X, Y\}, \forall u \in B\}$  
$\quad$Loop for each $v \in l$:  
$\qquad\oplus($"val" $v$ ":" "MutableOptional<" typeof($v$) ">" "=" "MutableOptional.empty"$)$
>
> $\qquad\oplus($"def" "get" $v$ ":" typeof($v$) "{" "this." $v$ "}"$)$  
>
>$\qquad\oplus($"def" "set" $v$ "(" "v" ":" typeof($v$) ")" "{"$)$  
$\qquad\oplus($"if" "(" "this." $v$ ".isPresent" ")" "{" $\boxtimes($Already bound$)$ "}"$)$  
$\qquad\oplus($"this." $v$ ".set(" $v$ ")"$)$  
$\qquad\oplus($"wake" "(" "new" $v$ "Bound" ")"$)$  
>
>$\qquad\Omega_1 = \{m | u = \langle X,Y,m,A,\square\rangle, v \in A, r \in \{X, Y\}, \forall u \in B\}$  
$\qquad$Loop for each $m \in \Omega_1$:  
$\qquad\quad\oplus($"wake" $m$ "IfEnabled"$)$  
$\qquad\oplus($"}"$)$  
>
> $\qquad$Loop for each $m \in \Omega_1$:  
$\qquad\quad\oplus($"private" "def" "wake" $m$ "IfEnabled" "{"$)$  
$\qquad\quad\oplus($"if" "("$)$  
$\qquad\quad$Loop for each $v \in \bigcup(A | u = \langle r,\square,m,A,\square\rangle, \forall u \in B)$:  
$\qquad\qquad\oplus($"this." v ".isPresent" "&&"$)$  
$\qquad\quad\oplus($")" "{"$)$  
$\qquad\quad\oplus($"wake" "(" "new" $m$ "Enabled" ")" "}" "}"$)$  
>
>$\qquad\Omega_2 = \{m | u = \langle X,Y,m,\square,E\rangle, v \in E, r \in \{X, Y\}, \forall u \in B\}$  
$\qquad$Loop for each $m \in \Omega_2$:  
$\qquad\quad\oplus($"emit" $m$ "IfReady"$)$  
$\qquad\oplus($"}"$)$  
>
> $\qquad$Loop for each $m \in \Omega_2$:  
$\qquad\quad\oplus($"private" "def" "emit" $m$ "IfReady" "{"$)$  
$\qquad\quad\oplus($"if" "("$)$  
$\qquad\quad\Omega_3 = \bigcup(E | u = \langle r,\square,m,\square,E\rangle, \forall u \in B)$  
$\qquad\quad$Loop for each $v \in \Omega_3$:  
$\qquad\qquad\oplus($"this." v ".isPresent" "&&"$)$  
$\qquad\quad\oplus($")" "{"$)$  
$\qquad\quad\oplus($"var" "evt" "=" "new" $m$ "("$)$  
$\qquad\quad$Loop for each $v \in \Omega_3$:  
$\qquad\qquad\oplus($"this." v$)$  
$\qquad\quad\oplus($")" "this.protocolSpace.emit" "(" "owner.ID" "," "evt" ")" "}" "}"$)$  
$\quad\oplus($"}"$)$


where $\boxtimes(x)$ generates an error with the message text $x$.

## 2.6. Definition of the receiver side

The players of the receiver roles are equiped with a behavior for reacting to the receiving of an event and to transform the received data from inside the event to knowledge in the receiving agent memory.

> Loop for each $r \in R_r$:  
$\quad\oplus($"behavior" $r$ "Behavior" "{"$)$  
>
>$\quad$If $\exists m | m \in M, u = \langle r,\square,m,\emptyset,\square\rangle, \forall u \in B$:  
$\qquad\oplus($"uses" "Behaviors"$)$  
$\qquad\oplus($"on" "ProtocolReady" "{" "wake" "(" "new" i "Enabled" ")" "}"$)$
>
>$\quad\oplus($"uses" $r$ "ProtocolCapacity"$)$  
>
>$\quad\Omega = \{m | u = \langle\square,r,m,\square,\square\rangle, \forall u \in B\}$  
$\quad$Loop for each $m \in \Omega$:  
$\qquad\oplus($"on" $m$ "{"$)$  
$\qquad$Loop for each $v \in \{v | v \in A \cup E, u = \langle \square,r,m,A,E\rangle, \forall u \in B\}$:  
$\qquad\qquad\oplus($"set" $v$ "(" "occurrence." $v$ ")"$)$  
$\qquad\oplus($"}"$)$  
$\quad\oplus($"}"$)$  

# 3. Generated Templates for Agents

The following rule describes the generation of a template code for an agent involved in a protocol.

> Loop for each $r \in R$:  
$\quad\oplus($"agent" $r$ "Agent" "{"$)$  
$\quad\oplus($"uses" "ProtocolEnactment"$)$  
$\quad\oplus($"uses" $r$ "ProtocolCapacity"$)$  
>
>$\quad\oplus($"val" "protocolSpace" "=" "new" "AtomicRerence<ProtocolSpace>"$)$  
>
>$\quad\oplus($"on" "SpaceCreated" "[" "it.spaceID.spaceSpecification" "==" $B$ "SpaceSpecification" "{"$)$  
$\quad$If ProtocolEnactmentSkill is not a built-in capacity in SARL:  
$\qquad\oplus($"setSkill(new ProtocolEnactmentSkill)"$)$  
$\quad\oplus($"var" "sp" "=" "enact" "(" "occurrence.spaceID" "," $B$ "Role." $r$ ")"$)$  
$\quad\oplus($"protocolSpace.set(sp)" "}"$)$  
>
>$\quad\Omega_1 = \{m | u = \langle r,\square,m,\square,\square\rangle, \forall u \in B\}$  
$\quad$Loop on $m \in \Omega_1$:  
$\qquad\oplus($"on" $m$ "Enabled" "{"$)$  
$\qquad\Omega_2 = \{v | v \in E, u = \langle r,\square,m,\square,E\rangle, \forall u \in B\}$  
$\qquad$Loop for each $v \in \Omega_2$:  
$\qquad\quad\oplus($"// TODO: Replace $\Delta$ by a value"$)$  
$\qquad\quad\oplus($"set" $v$ "(" "$\Delta$" ")"$)$  
$\qquad\oplus($"}"$)$  
>
>$\quad\oplus($"on" "ProtocolCompleted" "{" "}"$)$  
$\qquad\oplus($"// TODO: Leave the procotol"$)$  
$\quad\oplus($"}"$)$  

