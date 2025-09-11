import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule, MatCardModule, RouterModule],
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  events: any[] = [];
  filteredEvents: any[] = [];

  categories: string[] = [
    "CONFERENCE", "SEMINAR", "NETWORKING", "FESTIVAL",
    "FUNDRAISING", "MUSIC", "ARTS", "PERFORMANCE", "LECTURE",
    "FASHION", "KIDS_AND_FAMILY", "SPORTS", "FOOD_AND_DRINK",
    "FILM", "CONCERT", "TECHNOLOGY"
  ];

  selectedCategory = '';
  selectedSort = '';
  fromDate?: string;
  toDate?: string;
  fromTime: string = '';
  toTime: string = '';
  minPrice?: number;
  maxPrice?: number;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('/api/events').subscribe(data => {
      this.events = data;
      this.filteredEvents = data;
    });
  }

  applyFilters(): void {
    this.filteredEvents = this.events.filter(event => {
      const eventDate = new Date(event.date);
      const eventTime = event.time;

      const matchCategory = this.selectedCategory ? event.category === this.selectedCategory : true;
      const matchFromDate = this.fromDate ? eventDate >= new Date(this.fromDate) : true;
      const matchToDate = this.toDate ? eventDate <= new Date(this.toDate) : true;
      const matchFromTime = this.fromTime ? eventTime >= this.fromTime : true;
      const matchToTime = this.toTime ? eventTime <= this.toTime : true;
      const matchMinPrice = this.minPrice != null ? event.ticketPrice >= this.minPrice : true;
      const matchMaxPrice = this.maxPrice != null ? event.ticketPrice <= this.maxPrice : true;

      return (
        matchCategory &&
        matchFromDate &&
        matchToDate &&
        matchFromTime &&
        matchToTime &&
        matchMinPrice &&
        matchMaxPrice
      );
    });

    // Sorting
    if (this.selectedSort === 'date') {
      this.filteredEvents.sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
    } else if (this.selectedSort === 'priceLowHigh') {
      this.filteredEvents.sort((a, b) => a.ticketPrice - b.ticketPrice);
    } else if (this.selectedSort === 'priceHighLow') {
      this.filteredEvents.sort((a, b) => b.ticketPrice - a.ticketPrice);
    }
  }

  resetFilters(): void {
    this.selectedCategory = '';
    this.selectedSort = '';
    this.fromDate = '';
    this.toDate = '';
    this.fromTime = '';
    this.toTime = '';
    this.minPrice = undefined;
    this.maxPrice = undefined;
    this.filteredEvents = [...this.events];
  }
}
