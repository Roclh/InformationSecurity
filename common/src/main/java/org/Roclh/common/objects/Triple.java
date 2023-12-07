package org.Roclh.common.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triple<A, B, C> {

    private A first;
    private B second;
    private C third;
}