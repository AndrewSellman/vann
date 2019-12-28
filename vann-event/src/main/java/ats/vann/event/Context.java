package ats.vann.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Context {
    private final String networkName;
    private final int networkLayerIndex;
    private final Representation represents;

}
