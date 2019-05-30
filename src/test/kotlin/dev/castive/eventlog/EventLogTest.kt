/*
 *    Copyright 2019 Django Cass
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package dev.castive.eventlog

import dev.castive.eventlog.schema.Event
import dev.castive.eventlog.schema.EventType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EventLogTest {
	@BeforeEach
	internal fun setUp() {
		EventLog.stream.add(System.out)
	}

	@AfterEach
	internal fun tearDown() {
		EventLog.stream.rm(System.out)
	}

	@ParameterizedTest
	@ValueSource(ints = [0, 1])
	fun testGeneric(value: Int) {
		val testEvent = Event(type = EventType.CREATE, resource = Any::class.java, causedBy = null)
		EventLog.post(testEvent, value == 1)
	}
	@Test
	fun testNullCreator() {
		val testEvent = Event(type = EventType.CREATE, resource = Any::class.java, causedBy = null)
		val response = EventLog.post(testEvent, false)
		Assertions.assertTrue(response.contains("[Not specified]"))
	}
	@Test
	fun testNonNullCreator() {
		val testEvent = Event(type = EventType.CREATE, resource = Any::class.java, causedBy = Test::class.java)
		val response = EventLog.post(testEvent, false)
		Assertions.assertFalse(response.contains("[Not specified]"))
	}
}