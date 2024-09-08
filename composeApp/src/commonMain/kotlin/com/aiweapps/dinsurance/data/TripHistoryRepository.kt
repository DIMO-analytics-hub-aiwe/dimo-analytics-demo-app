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

    private val dates = listOf(
        "2024-09-07T09:40:50.000000000Z",
        "2024-08-25T15:20:10.000000000Z",
        "2024-10-02T18:05:30.000000000Z",
        "2024-07-19T12:45:00.000000000Z",
        "2024-11-01T09:00:15.000000000Z",
        "2024-12-31T22:50:40.000000000Z",
        "2024-01-15T14:30:25.000000000Z",
        "2024-03-05T17:15:55.000000000Z",
        "2024-06-22T13:25:05.000000000Z",
        "2024-04-11T19:35:45.000000000Z",
        "2024-05-08T08:40:20.000000000Z",
        "2024-02-28T21:55:35.000000000Z",
        "2024-09-03T10:05:50.000000000Z",
        "2024-07-27T11:50:10.000000000Z",
        "2024-10-12T16:20:30.000000000Z"
    )

    private val speeds = listOf(
        "72 mph",
        "94 mph",
        "63 mph",
        "110 mph",
        "81 mph",
        "56 mph",
        "99 mph",
        "68 mph",
        "102 mph",
        "88 mph",
        "75 mph",
        "93 mph",
        "79 mph",
        "57 mph",
        "84 mph"
    )

    private val liters = listOf(
        "10 L",
        "25 L",
        "15 L",
        "30 L",
        "5 L",
        "12 L",
        "20 L",
        "8 L",
        "18 L",
        "22 L",
        "6 L",
        "35 L",
        "40 L",
        "9 L",
        "28 L"
    )

    private val miles = listOf(
        "123 mi",
        "85 mi",
        "140 mi",
        "67 mi",
        "95 mi",
        "200 mi",
        "175 mi",
        "50 mi",
        "160 mi",
        "78 mi",
        "120 mi",
        "110 mi",
        "90 mi",
        "105 mi",
        "135 mi"
    )

    private val timeIntervals = listOf(
        "10:23-12:10",
        "08:15-09:45",
        "14:00-16:30",
        "09:10-10:50",
        "11:25-13:00",
        "15:00-17:15",
        "07:45-09:30",
        "12:20-14:10",
        "13:40-15:30",
        "16:45-18:00",
        "06:30-08:00",
        "09:50-11:40",
        "17:15-19:05",
        "10:00-11:20",
        "18:30-20:00"
    )

    fun getMockTripHistory(): List<TripHistoryItem> {
        val items = mutableListOf<TripHistoryItem.Item>()
        repeat(10) {
            val date = dates.random()
            val speed = speeds.random()
            val fuel = liters.random()
            val distance = miles.random()
            val travelTime = timeIntervals.random()
            val progress = Random.nextDouble(from = 0.0, until = 1.0).toFloat()
            items.add(
                TripHistoryItem.Item(
                    id = Uuid.random().toString(),
                    date = formatDateFromISO(isoString = date),
                    subtitle = "Trip rating",
                    timestamp = hoursAndMinutesFromISOAMPM(isoString = date),
                    progress = progress,
                    isoDate = date,
                    details = TripHistoryItem.Item.Details(
                        speed = speed,
                        fuel = fuel,
                        distance = distance,
                        travelTime = travelTime,
                    )
                )
            )
        }
        return groupTripsByWeek(trips = items)
    }

    private fun groupTripsByWeek(trips: List<TripHistoryItem.Item>): List<TripHistoryItem> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        val startOfCurrentWeek = today.minus(today.dayOfWeek.ordinal, DateTimeUnit.DAY)
        val startOfLastWeek = startOfCurrentWeek.minus(value = 7, unit = DateTimeUnit.DAY)

        return trips.sortedByDescending { it.date }.groupBy { trip ->
            val tripDate = Instant.parse(trip.isoDate).toLocalDateTime(TimeZone.UTC).date

            when {
                tripDate >= startOfCurrentWeek -> "This Week"
                tripDate >= startOfLastWeek -> "Last Week"
                else -> "${
                    tripDate.monthNumber.toString().padStart(2, '0')
                }/${
                    tripDate.dayOfMonth.toString().padStart(2, '0')
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