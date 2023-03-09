import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCourtsComponent } from './search-courts.component';

describe('SearchCourtsComponent', () => {
  let component: SearchCourtsComponent;
  let fixture: ComponentFixture<SearchCourtsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchCourtsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchCourtsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
