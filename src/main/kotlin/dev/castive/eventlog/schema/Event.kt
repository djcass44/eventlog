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

package dev.castive.eventlog.schema

import java.util.*

enum class EventType {
	CREATE, READ, UPDATE, DESTROY
}
data class Event(val uuid: UUID = UUID.randomUUID(),
                 val type: EventType,
                 val timestamp: Long = System.currentTimeMillis(),
                 val resource: Class<out Any>,
                 val causedBy: Class<out Any>?) {
	fun toSimpleString(): String {
		return "${resource.name} changed (${type.name}) on $timestamp, caused by ${causedBy?.name}"
	}
}