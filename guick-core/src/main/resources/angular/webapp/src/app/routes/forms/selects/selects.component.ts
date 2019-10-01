import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Person, DataService } from '../data.service';

@Component({
  selector: 'app-selects',
  templateUrl: './selects.component.html',
  providers: [DataService],
})
export class SelectsComponent implements OnInit {
  // Data source
  people$: Observable<Person[]>;
  people: Person[] = [];
  selectedPersonId = '5a15b13c36e7a7f00cf0d7cb';
  selectedPersonId2 = '5a15b13c36e7a7f00cf0d7cb';

  selectedSimpleItem = 'Two';
  simpleItems = [];
  disable = true;

  selectedCarId = 3;
  cars = [
    { id: 1, name: 'Volvo' },
    { id: 2, name: 'Saab', disabled: true },
    { id: 3, name: 'Opel' },
    { id: 4, name: 'Audi' },
  ];

  // Tags
  companies: any[] = [];
  loading = false;
  companiesNames = ['Miškas', 'Žalias', 'Flexigen'];
  selectedCompany = '';
  selectedCompanyCustom = '';
  selectedCompanyCustomPromise = '';

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.people$ = this.dataService.getPeople();
    this.dataService.getPeople().subscribe(items => (this.people = items));
    this.simpleItems = [true, 'Two', 3];

    this.companiesNames.forEach((c, i) => {
      this.companies.push({ id: i, name: c });
    });
  }

  toggleDisabled() {
    const car: any = this.cars[1];
    car.disabled = !car.disabled;
  }

  addTag(name: string) {
    return { name, tag: true };
  }

  addTagPromise(name: string) {
    return new Promise(resolve => {
      this.loading = true;
      setTimeout(() => {
        resolve({ id: 5, name, valid: true });
        this.loading = false;
      }, 1000);
    });
  }
}
