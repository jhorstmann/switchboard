package org.zalando.switchboard;

/*
 * ⁣​
 * Switchboard
 * ⁣⁣
 * Copyright (C) 2015 Zalando SE
 * ⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ​⁣
 */

import org.junit.Test;

import java.util.List;
import java.util.concurrent.Future;

import static java.util.Objects.hash;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public final class DeliveryTest {
    
    private final Switchboard board = Switchboard.create();
    
    @Test
    public void shouldCreateHashCode() {
        final Subscription<String, Object> subscription = "foo"::equals;
        final Future<List<String>> future = board.subscribe(subscription, 1);
        
        assertThat(future.hashCode(), is(hash(subscription)));
    }
    
    @Test
    public void shouldNotBeEqualToDifferentType() {
        final Subscription<String, Object> subscription = "foo"::equals;
        final Future<List<String>> future = board.subscribe(subscription, 1);
        
        assertThat(future, not(equalTo(subscription)));
    }

}