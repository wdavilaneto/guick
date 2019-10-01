import { Component, OnInit, ViewEncapsulation, Input, HostBinding } from '@angular/core';
import { MenuService } from '@core';
import { Router } from '@angular/router';

@Component({
  selector: 'page-header',
  host: {
    class: 'matero-page-header',
  },
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class PageHeaderComponent implements OnInit {
  @Input() title = '';
  @Input() subtitle = '';
  @Input() showBreadCrumb = true;

  constructor(private router: Router, private menuService: MenuService) {
    const states = this.router.url.slice(1).split('/');
    this.title = this.menuService.getMenuItemName(states);
  }

  ngOnInit() {}
}
