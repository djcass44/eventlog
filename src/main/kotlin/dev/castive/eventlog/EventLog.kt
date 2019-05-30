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

import dev.castive.eventlog.io.NOutputStream
import dev.castive.eventlog.schema.Event
import java.nio.charset.StandardCharsets

object EventLog {
	val stream = NOutputStream()

	fun post(event: Event, printFull: Boolean = false): String {
		val t = if(printFull) event.toString() else event.toSimpleString()
		stream.write("$t\n".toByteArray(StandardCharsets.UTF_8))
		return t
	}
}