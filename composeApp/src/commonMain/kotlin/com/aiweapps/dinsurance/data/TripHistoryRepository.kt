package com.aiweapps.dinsurance.data

import com.aiweapps.dinsurance.network.ApiService
import com.aiweapps.dinsurance.presentation.screens.triphistory.TripHistoryItem
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TripHistoryRepository(
    apiService: ApiService,
) {

    fun getMockTripHistory(): List<TripHistoryItem> {
        val initialList = listOf(
            TripHistoryItem.Item(
                id = Uuid.random().toString(),
                date = formatDateFromISO(isoString = "2024-09-07T09:40:50.000000000Z"),
                subtitle = "Trip rating",
                timestamp = hoursAndMinutesFromISOAMPM(isoString = "2024-09-07T09:40:50.000000000Z"),
                progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat(),
                isoDate = "2024-09-07T09:40:50.000000000Z",
                details = TripHistoryItem.Item.Details(
                    speed = "40 mph",
                    fuel = "10 L",
                    distance = "123 mi",
                    travelTime = "09:40-10:40",
                )
            ),
            TripHistoryItem.Item(
                id = Uuid.random().toString(),
                date = formatDateFromISO(isoString = "2024-09-06T10:23:30.000000000Z"),
                subtitle = "Trip rating",
                timestamp = hoursAndMinutesFromISOAMPM(isoString = "2024-09-06T10:23:30.000000000Z"),
                progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat(),
                isoDate = "2024-09-06T10:23:30.000000000Z",
                details = TripHistoryItem.Item.Details(
                    speed = "30 mph",
                    fuel = "20 L",
                    distance = "13 mi",
                    travelTime = "10:23-12:10",
                )
            ),
            TripHistoryItem.Item(
                id = Uuid.random().toString(),
                date = formatDateFromISO(isoString = "2024-08-30T12:33:10.000000000Z"),
                subtitle = "Trip rating",
                timestamp = hoursAndMinutesFromISOAMPM(isoString = "2024-08-30T12:33:10.000000000Z"),
                progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat(),
                isoDate = "2024-08-30T12:33:10.000000000Z",
                details = TripHistoryItem.Item.Details(
                    speed = "110 mph",
                    fuel = "50 L",
                    distance = "53 mi",
                    travelTime = "12:33-13:10",
                )
            ),
            TripHistoryItem.Item(
                id = Uuid.random().toString(),
                date = formatDateFromISO(isoString = "2024-07-29T14:12:10.000000000Z"),
                subtitle = "Trip rating",
                timestamp = hoursAndMinutesFromISOAMPM(isoString = "2024-07-29T14:12:10.000000000Z"),
                progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat(),
                isoDate = "2024-07-29T14:12:10.000000000Z",
                details = TripHistoryItem.Item.Details(
                    speed = "89 mph",
                    fuel = "30 L",
                    distance = "63 mi",
                    travelTime = "14:12-15:30",
                )
            ),
            TripHistoryItem.Item(
                id = Uuid.random().toString(),
                date = formatDateFromISO(isoString = "2024-06-14T21:12:10.000000000Z"),
                subtitle = "Trip rating",
                timestamp = hoursAndMinutesFromISOAMPM(isoString = "2024-06-14T21:12:10.000000000Z"),
                progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat(),
                isoDate = "2024-06-14T21:12:10.000000000Z",
                details = TripHistoryItem.Item.Details(
                    speed = "86 mph",
                    fuel = "43 L",
                    distance = "233 mi",
                    travelTime = "21:12-23:24",
                )
            )
        )
        return groupTripsByWeek(trips = initialList)
    }

    private fun groupTripsByWeek(trips: List<TripHistoryItem.Item>): List<TripHistoryItem> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        val startOfCurrentWeek = today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY)
        val startOfLastWeek = startOfCurrentWeek.minus(7, DateTimeUnit.DAY)

        return trips.groupBy { trip ->
            val tripDate = Instant.parse(trip.isoDate).toLocalDateTime(TimeZone.UTC).date

            when {
                tripDate >= startOfCurrentWeek -> "This Week"
                tripDate >= startOfLastWeek -> "Last Week"
                else -> "${
                    tripDate.dayOfMonth.toString().padStart(2, '0')
                }/${
                    tripDate.monthNumber.toString().padStart(2, '0')
                }/${tripDate.year}"
            }
        }.flatMap { (key, tripList) ->
            listOf(
                TripHistoryItem.Row(
                    id = Uuid.random().toString(),
                    title = key
                )
            ) + tripList
        }
    }

    private fun formatDateFromISO(isoString: String): String {
        val instant = Instant.parse(isoString)
        val date = instant.toLocalDateTime(TimeZone.UTC).date
        return "${date.monthNumber.toString().padStart(2, '0')}/${
            date.dayOfMonth.toString().padStart(2, '0')
        }/${date.year}"
    }

    private fun hoursAndMinutesFromISO(isoString: String): String {
        val instant = Instant.parse(isoString)
        val dateTime = instant.toLocalDateTime(TimeZone.UTC)
        val hours = dateTime.hour
        val minutes = dateTime.minute
        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
    }

    private fun hoursAndMinutesFromISOAMPM(isoString: String): String {
        val instant = Instant.parse(isoString)
        val dateTime = instant.toLocalDateTime(TimeZone.UTC)
        val hours24 = dateTime.hour
        val minutes = dateTime.minute
        val amPm = if (hours24 >= 12) "PM" else "AM"
        val hours12 = if (hours24 % 12 == 0) 12 else hours24 % 12
        return "${hours12.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')} $amPm"
    }


}