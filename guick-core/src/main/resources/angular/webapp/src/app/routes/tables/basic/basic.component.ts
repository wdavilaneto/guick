import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { BasicEditComponent } from './edit/edit.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.scss'],
  providers: [DataService],
})
export class BasicComponent implements OnInit {
  columns = [
    {
      title: 'Position',
      index: 'position',
      width: 'auto',
      fixed: 'left',
    },
    {
      title: 'Name',
      index: 'name',
      width: 'auto',
    },
    {
      title: 'Weight',
      index: 'weight',
      width: 'auto',
    },
    {
      title: 'Symbol',
      index: 'symbol',
      width: 'auto',
    },
    {
      title: 'Gender',
      index: 'gender',
      width: 'auto',
    },
    {
      title: 'Mobile',
      index: 'mobile',
      width: 'auto',
    },
    {
      title: 'Tele',
      index: 'tele',
      width: 'auto',
    },
    {
      title: 'City',
      index: 'city',
      width: 'auto',
    },
    {
      title: 'Address',
      index: 'address',
      width: 'auto',
    },
    {
      title: 'Date',
      index: 'date',
      width: 'auto',
    },
    {
      title: 'Website',
      index: 'website',
      width: 'auto',
    },
    {
      title: 'Company',
      index: 'company',
      width: 'auto',
    },
    {
      title: 'Email',
      index: 'email',
      width: 'auto',
    },
    {
      title: 'Options',
      index: 'options',
      width: 'auto',
    },
  ];

  displayedColumns: string[] = this.columns.map(item => item.index);
  dataSource = this.dataService.getData();

  constructor(private dataService: DataService, public dialog: MatDialog) {}

  ngOnInit() {}

  edit(value: any) {
    const dialogRef = this.dialog.open(BasicEditComponent, {
      width: '600px',
      data: { record: value },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
