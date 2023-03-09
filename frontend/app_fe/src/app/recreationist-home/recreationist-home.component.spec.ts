import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecreationistHomeComponent } from './recreationist-home.component';

describe('RecreationistComponent', () => {
  let component: RecreationistHomeComponent;
  let fixture: ComponentFixture<RecreationistHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecreationistHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecreationistHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
