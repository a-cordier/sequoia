# MINIMIDIM

Builting a midi synth scripting environment based on the processing minim library

## Core Components

### AbstractMidiSequencer

Sequencer should be responsible for setting a tempo, playing a sequence, and sending events to instruments or effects

### MidiInstrument

The MidiInstrument interface extends the minim Instrument interface. It defines a minim instrument with the capability to be tied to an instance of any class extending AbstractMidiSequencer

### MidiReceiver

The MidiReceiver class is and implementation of javax.sound.midi.Receiver interface. It is used by MidiInstrument implementations to handle midi messages received from any concrete instance of AbstractMidiSequencer
