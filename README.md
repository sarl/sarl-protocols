# sarl-protocols
Implementation of protocols in SARL

## SARL Metamodel

The main SARL metamodel is:

![SARL Metamodel](SARL_metamodel.png)

## Relation between SARL and CRIO/ASPECS metamodels

The relations between the SARL main metamodel and the concepts that are defined in the CRIO/ASPECS metamodel may be described as:

![SARL/CRIO Metamodel](SARL_CRIO_organizations.png)

## BSPL-like Specification

This projects aims at integrating the concept of protocol in a multiagent system coded with SARL.
BSPL was selected as a source of inspiration for the definition of the protocol concepts.
The variant of the BSPL metamodel that is used in this project is:

![BSPL Metamodel](BSPL_protocol.png)

## Relation between SARL and BSPL-like Specification

The relations between the SARL concepts and those from BSPL-like specification are below.
The concepts that are drawn with a yellow background are those that need to be implemented in SARL for implementing the protocol (see next section).

![BSPL-SARL Relations](BSPL_to_SARL.png)

Some concepts have been implemented independently of any protocol: `ProtocolSpace`, `ProtocolEnactment`.

Some concepts have been implemented with a generic part (independent of any protocol) and protocol-specific part: `ProtocolRole`.

Some concepts are implemented as protocol-specific: `ProtocolCapacity`, `ProtocolSkill`, `ProtocolBehavior`, `Message`, `Variable`, `VariableBound`.

## Transformations from BSPL-like specification to SARL

See the translation rules from BSPL-like specification to SARL in [this file](TRANSLATION_RULES.md).
